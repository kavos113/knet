package com.github.kavos113.knet.lib

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import okio.Path
import okio.Path.Companion.toPath

object PathSerializer : KSerializer<Path> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("okio.path", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Path) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): Path {
        return decoder.decodeString().toPath()
    }
}

object PathListSerializer : KSerializer<List<Path>> by ListSerializer(PathSerializer)