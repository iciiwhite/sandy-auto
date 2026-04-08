package com.sandyauto.ai

import com.sandyauto.core.Command
import com.sandyauto.core.DeviceContext
import com.sandyauto.utils.SecureStorage

class MistralService(private val secureStorage: SecureStorage) : AIService {
    override suspend fun decideNextAction(context: DeviceContext): Command {
        return Command.Wait(1000)
    }
    override suspend fun analyzeScreen(context: DeviceContext): String = ""
}