package com.sandyauto.di

import com.sandyauto.ai.AIService
import com.sandyauto.ai.GeminiService
import com.sandyauto.core.AutomationEngine
import com.sandyauto.data.repository.AccountRepository
import com.sandyauto.data.repository.ProjectRepository
import com.sandyauto.data.repository.SettingsRepository
import com.sandyauto.utils.SecureStorage
import org.koin.dsl.module

object AppModule {
    val module = module {
        single<AIService> { GeminiService(get()) }
        single { SecureStorage() }
        single { AccountRepository(get()) }
        single { ProjectRepository() }
        single { SettingsRepository() }
        single { AutomationEngine(get(), get(), get()) }
    }
}