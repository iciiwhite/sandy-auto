package com.sandyauto.data.repository

import com.sandyauto.data.Account
import com.sandyauto.utils.SecureStorage

class AccountRepository(private val secureStorage: SecureStorage) {
    suspend fun getAll(): List<Account> = emptyList()
    suspend fun add(account: Account) {}
    suspend fun remove(account: Account) {}
}