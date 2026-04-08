package com.sandyauto.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sandyauto.App

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Sandy Auto") {
        App()
    }
}