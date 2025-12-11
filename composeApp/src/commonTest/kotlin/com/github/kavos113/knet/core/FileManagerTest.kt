package com.github.kavos113.knet.core

import com.github.kavos113.knet.domain.FileItem
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.fakefilesystem.FakeFileSystem
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FileManagerTest {
    private lateinit var fakeFs: FileSystem
    private lateinit var fakeManager: FileManager

    @BeforeTest
    fun setup() {
        fakeFs = FakeFileSystem()
        fakeManager = FileManager(fileSystem = fakeFs)
    }

    @AfterTest
    fun tearDown() {

    }

    @Test
    fun testCreateFile_Success() {
        val path = "/sample.md".toPath()
        fakeManager.createFile(path)

        assertTrue(fakeFs.exists(path))
    }

    @Test
    fun testCreateFile_CreateParentDirectory() {
        val path = "/dir/sample2.md".toPath()
        fakeManager.createFile(path)

        assertTrue(fakeFs.exists(path))
    }

    @Test
    fun testCreateDirectory_Success() {
        val path = "/dir".toPath()
        fakeManager.createDirectory(path)

        assertTrue(fakeFs.exists(path))
    }

    @Test
    fun testCreateDirectory_Recursive() {
        val path = "/dir/dir2/dir3".toPath()
        fakeManager.createDirectory(path)

        assertTrue(fakeFs.exists(path))
    }

    @Test
    fun testWriteFile_CreateNotExistingFile() {
        val path = "/sample.md".toPath()
        val data = "sample text"
        fakeManager.writeFile(path, data)

        assertTrue(fakeFs.exists(path))
        assertEquals(data, fakeFs.read(path){ readUtf8() })
    }

    @Test
    fun testWriteFile_Overwrite() {
        val path = "/sample.md".toPath()
        val oldData = "sample text"
        val newData = "new text"
        fakeManager.writeFile(path, oldData)

        fakeManager.writeFile(path, newData)

        assertTrue(fakeFs.exists(path))
        assertEquals(newData, fakeFs.read(path){ readUtf8() })
    }

    @Test
    fun testReadFile_Success() {
        val path = "/sample.md".toPath()
        val data = "sample text"
        fakeManager.writeFile(path, data)

        assertEquals(data, fakeManager.readFile(path))
    }

    @Test
    fun testWriteBinaryFile_CreateNotExistingFile() {
        val path = "/sample.md".toPath()
        val data = "sample text".toByteArray()
        fakeManager.writeBinaryFile(path, data)

        assertTrue(fakeFs.exists(path))
        assertEquals(data.size, fakeFs.read(path){ readByteArray() }.size)
    }

    @Test
    fun testWriteBinaryFile_Overwrite() {
        val path = "/sample.md".toPath()
        val oldData = "sample text".toByteArray()
        val newData = "new text".toByteArray()
        fakeManager.writeBinaryFile(path, oldData)

        fakeManager.writeBinaryFile(path, newData)

        assertTrue(fakeFs.exists(path))
        assertEquals(newData.size, fakeFs.read(path){ readByteArray() }.size)
    }

    @Test
    fun testReadBinaryFile_Success() {
        val path = "/sample.md".toPath()
        val data = "sample text".toByteArray()
        fakeManager.writeBinaryFile(path, data)

        assertEquals(data.size, fakeManager.readBinaryFile(path).size)
    }

    @Test
    fun testGetFileTree_Success() {
        fakeFs.createDirectories("/dir1/dir2".toPath())
        fakeFs.write("/sample.md".toPath()) { }
        fakeFs.write("/dir1/sample.md".toPath()) { }
        fakeFs.write("/dir1/sample2.md".toPath()) { }
        fakeFs.write("/dir1/dir2/sample.md".toPath()) { }

        val expected = listOf(
            FileItem.DirectoryEntry(
                name = "dir1",
                path = "/dir1".toPath(),
                children = listOf(
                    FileItem.DirectoryEntry(
                        name = "dir2",
                        path = "/dir1/dir2".toPath(),
                        children = listOf(
                            FileItem.FileEntry(
                                name = "sample.md",
                                path = "/dir1/dir2/sample.md".toPath(),
                                size = 0
                            )
                        )
                    ),
                    FileItem.FileEntry(
                        name = "sample.md",
                        path = "/dir1/sample.md".toPath(),
                        size = 0
                    ),
                    FileItem.FileEntry(
                        name = "sample2.md",
                        path = "/dir1/sample2.md".toPath(),
                        size = 0
                    ),
                )
            ),
            FileItem.FileEntry(
                name = "sample.md",
                path = "/sample.md".toPath(),
                size = 0
            ),
        )

        assertEquals(expected, fakeManager.getFileTree("/".toPath()))
    }

    @Test
    fun testGetFileTree_Empty() {
        fakeFs.createDirectory("/dir".toPath())

        assertEquals(emptyList<FileItem>(), fakeManager.getFileTree("/dir".toPath()))
    }

    @Test
    fun testGetKeywordContent_Success() {
        val path = "/sample.md".toPath()
        val name = "test"
        val content = "content"
        val data = """
            <keyword name="$name">$content</keyword>
        """.trimIndent()
        fakeManager.writeFile(path, data)

        assertEquals(content, fakeManager.getKeywordContent(path, name))
    }

    @Test
    fun testGetKeywordContent_Multiline() {
        val path = "/sample.md".toPath()
        val name = "test"
        val content = "firs tline\nsecond line\n\nmany line"
        val data = """
            <keyword name="$name">$content</keyword>
        """.trimIndent()
        fakeManager.writeFile(path, data)

        assertEquals(content, fakeManager.getKeywordContent(path, name))
    }

    @Test
    fun testGetKeywordContent_IncludeOtherTags() {
        val path = "/sample.md".toPath()
        val name = "test"
        val content = "<details>some details</details>"
        val data = """
            <keyword name="$name">$content</keyword>
        """.trimIndent()
        fakeManager.writeFile(path, data)

        assertEquals(content, fakeManager.getKeywordContent(path, name))
    }

    @Test
    fun testGetKeywordContent_KeywordIncludeKeyword() {
        val path = "/sample.md".toPath()
        val name = "keyword"
        val content = "content"
        val data = """
            <keyword name="$name">$content</keyword>
        """.trimIndent()
        fakeManager.writeFile(path, data)

        assertEquals(content, fakeManager.getKeywordContent(path, name))
    }

    @Test
    fun testGetKeywordContent_SearchFromMultipleKeywords() {
        val path = "/sample.md".toPath()
        val name = "test"
        val content = "content"
        val data = """
            <keyword name="$name">$content</keyword>
            <keyword name="${name}2">$content $content</keyword>
            <keyword name="${name}3">$content $content $content</keyword>
        """.trimIndent()
        fakeManager.writeFile(path, data)

        assertEquals(content, fakeManager.getKeywordContent(path, name))
    }

}