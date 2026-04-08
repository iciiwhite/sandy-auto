package com.sandyauto.ai

import com.sandyauto.utils.SecureStorage

actual class GeminiService actual constructor(secureStorage: SecureStorage) : AIService by GeminiServiceBase(secureStorage) {
}