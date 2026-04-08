package com.sandyauto.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.sandyauto.data.Account

@Composable
fun AccountScreen() {
    val accounts = remember { mutableStateListOf<Account>() }
    Column {
        Button(onClick = { accounts.add(Account("New Service", "", "")) }) {
            Text("Add Account")
        }
        accounts.forEach { account ->
            Text("${account.serviceName}: ${account.username}")
        }
    }
}