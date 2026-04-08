package com.sandyauto.data.repository

import com.sandyauto.data.Settings

class SettingsRepository {
    suspend fun get(): Settings = Settings()
    suspend fun save(settings: Settings) {}
}