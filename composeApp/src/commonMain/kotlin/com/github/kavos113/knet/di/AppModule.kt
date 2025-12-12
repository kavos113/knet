package com.github.kavos113.knet.di

import com.github.kavos113.knet.core.FileManager
import com.github.kavos113.knet.ui.explorer.ExplorerViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { FileManager() }

    viewModelOf(::ExplorerViewModel)
}
