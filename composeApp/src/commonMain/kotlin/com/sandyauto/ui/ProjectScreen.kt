package com.sandyauto.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.sandyauto.data.Project

@Composable
fun ProjectScreen() {
    val projects = remember { mutableStateListOf<Project>() }
    Column {
        Button(onClick = { projects.add(Project("New Project", "")) }) {
            Text("Create Project")
        }
        projects.forEach { project ->
            Text("${project.name}: ${project.description}")
        }
    }
}