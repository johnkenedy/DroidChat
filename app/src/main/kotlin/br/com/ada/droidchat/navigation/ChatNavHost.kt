package br.com.ada.droidchat.navigation

import SignUpRoute
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import br.com.ada.droidchat.ui.feature.signin.SignInRoute
import br.com.ada.droidchat.ui.feature.splash.SplashRoute

@Composable
fun ChatNavHost(
    appNavigator: AppNavigator = remember { AppNavigator() }
) {

    NavDisplay(
        backStack = appNavigator.backStack,
        onBack = { appNavigator.onBack() },
        entryProvider = entryProvider {
            entry<NavKey.SplashRoute> { SplashRoute(onNavigateToSignIn = { appNavigator.navigateToSignIn(true) }) }
            entry<NavKey.SignInRoute> { SignInRoute(navigateToSignUp = { appNavigator.navigateToSignUp() }, navigateToHome = { appNavigator.navigateToHomeAndClearStack() }) }
            entry<NavKey.SignUpRoute> { SignUpRoute(onSignUpSuccess = { appNavigator.onBack() }) }
            entry<NavKey.HomeRoute> { Unit }
        }
    )

}