package com.sandyauto.utils

actual class Logger {
    actual fun d(tag: String, message: String) = println("DEBUG: $tag - $message")
    actual fun e(tag: String, message: String, throwable: Throwable?) {
        System.err.println("ERROR: $tag - $message")
        throwable?.printStackTrace()
    }
}