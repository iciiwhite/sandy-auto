package com.sandyauto.ai

import com.sandyauto.core.Command
import com.sandyauto.core.DeviceContext
import com.sandyauto.utils.SecureStorage
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class GeminiService(private val secureStorage: SecureStorage) : AIService {
    private val client = HttpClient()
    private val json = Json { ignoreUnknownKeys = true }

    @Serializable
    data class GeminiRequest(val prompt: String)
    @Serializable
    data class GeminiResponse(val text: String)

    override suspend fun decideNextAction(context: DeviceContext): Command {
        val apiKey = secureStorage.get("gemini_api_key") ?: ""
        val prompt = buildPrompt(context)
        val response = client.post("https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=$apiKey") {
            contentType(ContentType.Application.Json)
            setBody(GeminiRequest(prompt))
        }.bodyAsText()
        val result = json.decodeFromString<GeminiResponse>(response)
        return parseCommand(result.text)
    }

    override suspend fun analyzeScreen(context: DeviceContext): String {
        return "Screen analysis not implemented"
    }

    private fun buildPrompt(context: DeviceContext): String = ""
    private fun parseCommand(text: String): Command = Command.Wait(1000)
}