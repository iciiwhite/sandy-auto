1package com.sandyauto.data

data class Account(
    val serviceName: String,
    val username: String,
    val encryptedToken: String
)