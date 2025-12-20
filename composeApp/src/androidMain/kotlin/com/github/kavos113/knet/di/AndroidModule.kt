package com.github.kavos113.knet.di

import com.github.kavos113.knet.lib.AndroidAppConfig
import com.github.kavos113.knet.lib.AppConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single<AppConfig> { AndroidAppConfig(androidContext()) }
}