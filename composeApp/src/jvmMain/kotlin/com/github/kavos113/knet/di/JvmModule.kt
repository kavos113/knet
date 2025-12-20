package com.github.kavos113.knet.di

import com.github.kavos113.knet.lib.AppConfig
import com.github.kavos113.knet.lib.JvmAppConfig
import org.koin.dsl.module

val jvmModule = module {
    single<AppConfig> { JvmAppConfig() }
}