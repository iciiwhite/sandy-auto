package com.sandyauto.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen() {
    Column {
        Text("Settings")
        Text("AI Provider: Gemini")
        Text("Automation enabled: true")
    }
}