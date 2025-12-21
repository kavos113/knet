package com.github.kavos113.knet.di

import com.github.kavos113.knet.lib.AndroidOsConfig
import com.github.kavos113.knet.lib.OsConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single<OsConfig> { AndroidOsConfig(androidContext()) }
}