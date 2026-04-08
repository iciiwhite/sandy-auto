package com.sandyauto.utils

expect class Logger() {
    fun d(tag: String, message: String)
    fun e(tag: String, message: String, throwable: Throwable? = null)
}