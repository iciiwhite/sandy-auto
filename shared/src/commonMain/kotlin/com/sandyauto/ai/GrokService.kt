package com.sandyauto.ai

import com.sandyauto.core.Command
import com.sandyauto.core.DeviceContext
import com.sandyauto.utils.SecureStorage

class GrokService(private val secureStorage: SecureStorage) : AIService {
    override suspend fun decideNextAction(context: DeviceContext): Command = Command.Wait(1000)
    override suspend fun analyzeScreen(context: DeviceContext): String = ""
}