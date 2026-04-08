package com.sandyauto.utils

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

actual class SecureStorage {
    private val context: Context = getApplicationContext()
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private val sharedPreferences = EncryptedSharedPreferences.create(
        "sandy_secure_prefs",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    actual fun get(key: String): String? = sharedPreferences.getString(key, null)
    actual fun set(key: String, value: String) = sharedPreferences.edit().putString(key, value).apply()
    actual fun remove(key: String) = sharedPreferences.edit().remove(key).apply()

    private fun getApplicationContext(): Context {
        return Class.forName("android.app.ActivityThread")
            .getMethod("currentApplication")
            .invoke(null) as Context
    }
}