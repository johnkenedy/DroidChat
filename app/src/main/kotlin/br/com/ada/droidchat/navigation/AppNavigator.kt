package br.com.ada.droidchat.navigation
//
//import androidx.compose.runtime.Stable
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.snapshots.SnapshotStateList
//
//@Stable
//class AppNavigator {
//    val backStack: SnapshotStateList<Any> = mutableStateListOf(SplashRoute)
//
//    fun onBack() {
//        backStack.removeLastOrNull()
//    }
//
//    fun navigateToSignIn(clearSplash: Boolean = false) {
//        if (clearSplash) {
//            backStack.remove(SplashRoute)
//        }
//        if (backStack.lastOrNull() != SignInRoute) {
//            backStack.add(SignInRoute)
//        }
//    }
//
//    fun navigateToSignUp() {
//        if (backStack.lastOrNull() != SignUpRoute) {
//            backStack.add(SignUpRoute)
//        }
//    }
//
//    fun navigateToHomeAndClearStack() {
//        backStack.clear()
//        backStack.add(SplashRoute)
//    }
//}