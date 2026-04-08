package com.sandyauto

import androidx.compose.runtime.Composable
import org.koin.compose.KoinApplication
import com.sandyauto.di.AppModule
import com.sandyauto.ui.MainScreen

@Composable
fun App() {
    KoinApplication(application = {
        modules(AppModule.module)
    }) {
        MainScreen()
    }
}