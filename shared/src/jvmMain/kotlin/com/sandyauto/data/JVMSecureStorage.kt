package com.sandyauto.utils

import java.util.prefs.Preferences

actual class SecureStorage {
    private val prefs = Preferences.userRoot().node("com/sandyauto")

    actual fun get(key: String): String? = prefs.get(key, null)
    actual fun set(key: String, value: String) = prefs.put(key, value)
    actual fun remove(key: String) = prefs.remove(key)
}