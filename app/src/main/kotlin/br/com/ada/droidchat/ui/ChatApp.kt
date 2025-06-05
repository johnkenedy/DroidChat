package br.com.ada.droidchat.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.ada.droidchat.navigation.ChatNavHost

@Composable
fun ChatApp() {
    Scaffold(
        bottomBar = { }
    ) { innerPaddings ->
        Box(
            modifier = Modifier
                .consumeWindowInsets(innerPaddings)
                .padding(innerPaddings)
                .imePadding()
                .fillMaxSize()
        ) {
            ChatNavHost()
        }
    }
}