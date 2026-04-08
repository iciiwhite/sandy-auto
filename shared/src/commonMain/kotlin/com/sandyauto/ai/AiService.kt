package com.sandyauto.ai

import com.sandyauto.core.Command
import com.sandyauto.core.DeviceContext

interface AIService {
    suspend fun decideNextAction(context: DeviceContext): Command
    suspend fun analyzeScreen(context: DeviceContext): String
}