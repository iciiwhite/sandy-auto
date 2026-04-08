package com.sandyauto.utils

expect class SecureStorage() {
    fun get(key: String): String?
    fun set(key: String, value: String)
    fun remove(key: String)
}