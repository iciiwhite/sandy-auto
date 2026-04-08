package com.sandyauto.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.sandyauto.core.AutomationEngine
import com.sandyauto.data.repository.AccountRepository
import com.sandyauto.data.repository.ProjectRepository

class MainViewModel(
    private val automationEngine: AutomationEngine,
    private val accountRepo: AccountRepository,
    private val projectRepo: ProjectRepository
) {
    val isRunning = mutableStateOf(false)

    fun startAutomation() {
        automationEngine.start()
        isRunning.value = true
    }

    fun stopAutomation() {
        automationEngine.stop()
        isRunning.value = false
    }
}