package br.com.ada.droidchat.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import br.com.ada.droidchat.ui.feature.splash.SplashScreen
import kotlinx.serialization.Serializable

@Serializable
object SplashRoute: NavKey

@Serializable
object SignInRoute: NavKey

@Serializable
object SignUpRoute: NavKey


@Composable
fun ChatNavHost() {

    val backStack = remember { mutableStateListOf<Any>(SplashRoute) }

    val navigateToSignIn = { backStack.add(SignInRoute) }

    val navigateToSignUp = { backStack.add(SignUpRoute) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<SplashRoute> { SplashScreen(onTimeout = navigateToSignIn) }
            entry<SignInRoute> { Text("Sign In") }
            entry<SignUpRoute> { Text("Sign Up") }
        }
    )

}