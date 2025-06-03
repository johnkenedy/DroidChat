package br.com.ada.droidchat.ui.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.ada.droidchat.R
import br.com.ada.droidchat.ui.theme.BackgroundGradient
import br.com.ada.droidchat.ui.theme.DroidChatTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateToSignIn: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000)
        onNavigateToSignIn()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = BackgroundGradient)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo"
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
            ) {
            Image(
                painter = painterResource(R.drawable.ic_safety),
                contentDescription = "Safety"
            )

            Spacer(Modifier.width(10.dp))

            Text(
                text = stringResource(R.string.splash_safety_info),
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    DroidChatTheme {
        SplashScreen(onNavigateToSignIn = { false })
    }
}
