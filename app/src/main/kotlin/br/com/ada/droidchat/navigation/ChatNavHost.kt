package br.com.ada.droidchat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.ada.droidchat.ui.feature.signin.SignInRoute
import br.com.ada.droidchat.ui.feature.splash.SplashRoute

const val SPLASH_ROUTE = "splash"
const val SIGN_IN_ROUTE = "signIn"
const val SIGN_UP_ROUTE = "signUp"

@Composable
fun ChatNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SPLASH_ROUTE) {
        composable(SPLASH_ROUTE) {
            SplashRoute()
        }
        composable(SIGN_IN_ROUTE) {
            SignInRoute()
        }
        composable(SIGN_UP_ROUTE) {

        }

    }
}

//NEW NAV3
//@Composable
//fun ChatNavHost(
//    appNavigator: AppNavigator = remember { AppNavigator() }
//) {
//
//    NavDisplay(
//        backStack = appNavigator.backStack,
//        onBack = { appNavigator.onBack() },
//        entryProvider = entryProvider {
//            entry<SplashRoute> { SplashScreen(onNavigateToSignIn = { appNavigator.navigateToSignIn(true) }) }
//            entry<SignInRoute> { SignInScreen() }
//            entry<SignUpRoute> { Text("Sign Up") }
//        }
//    )
//
//}