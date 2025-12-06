package com.github.kavos113.knet

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform