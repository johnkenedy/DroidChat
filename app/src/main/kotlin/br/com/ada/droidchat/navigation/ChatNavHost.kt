package br.com.ada.droidchat.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import br.com.ada.droidchat.ui.feature.splash.SplashScreen

@Composable
fun ChatNavHost(
    appNavigator: AppNavigator = remember { AppNavigator() }
) {

    NavDisplay(
        backStack = appNavigator.backStack,
        onBack = { appNavigator.onBack() },
        entryProvider = entryProvider {
            entry<SplashRoute> { SplashScreen(onTimeout = { appNavigator.navigateToSignIn(true) }) }
            entry<SignInRoute> { Text("Sign In") }
            entry<SignUpRoute> { Text("Sign Up") }
        }
    )

}