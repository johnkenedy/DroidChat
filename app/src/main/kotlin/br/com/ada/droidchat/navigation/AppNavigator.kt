package br.com.ada.droidchat.navigation

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

@Stable
class AppNavigator {
    val backStack: SnapshotStateList<Any> = mutableStateListOf(NavKey.SplashRoute)

    fun onBack() {
        backStack.removeLastOrNull()
    }

    fun navigateToSignIn(clearSplash: Boolean = false) {
        if (clearSplash) {
            backStack.remove(NavKey.SplashRoute)
        }
        if (backStack.lastOrNull() != NavKey.SignInRoute) {
            backStack.add(NavKey.SignInRoute)
        }
    }

    fun navigateToSignUp() {
        if (backStack.lastOrNull() != NavKey.SignUpRoute) {
            backStack.add(NavKey.SignUpRoute)
        }
    }

    fun navigateToHomeAndClearStack() {
        backStack.clear()
        backStack.add(NavKey.SplashRoute)
    }
}