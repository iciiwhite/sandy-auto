package com.sandyauto.data

data class Project(
    val name: String,
    val description: String,
    val bots: List<String> = emptyList()
)