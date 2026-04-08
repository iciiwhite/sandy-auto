package com.sandyauto.core

data class Bot(
    val id: String,
    val name: String,
    val aiModel: String,
    val isActive: Boolean = false
)