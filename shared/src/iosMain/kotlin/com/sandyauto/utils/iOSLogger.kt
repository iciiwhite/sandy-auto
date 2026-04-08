package com.sandyauto.utils

import platform.Foundation.NSLog

actual class Logger {
    actual fun d(tag: String, message: String) = NSLog("$tag: $message")
    actual fun e(tag: String, message: String, throwable: Throwable?) = NSLog("$tag ERROR: $message")
}