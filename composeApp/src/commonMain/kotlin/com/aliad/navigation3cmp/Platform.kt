package com.aliad.navigation3cmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform