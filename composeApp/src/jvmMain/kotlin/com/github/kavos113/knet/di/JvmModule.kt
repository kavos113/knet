package com.github.kavos113.knet.di

import com.github.kavos113.knet.lib.OsConfig
import com.github.kavos113.knet.lib.JvmOsConfig
import org.koin.dsl.module

val jvmModule = module {
    single<OsConfig> { JvmOsConfig() }
}