package com.sandyauto.utils

import platform.Foundation.NSUserDefaults

actual class SecureStorage {
    private val defaults = NSUserDefaults.standardUserDefaults

    actual fun get(key: String): String? = defaults.stringForKey(key)
    actual fun set(key: String, value: String) = defaults.setObject(value, key)
    actual fun remove(key: String) = defaults.removeObjectForKey(key)
}