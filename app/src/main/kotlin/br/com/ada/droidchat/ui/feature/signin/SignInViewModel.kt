package br.com.ada.droidchat.ui.feature.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    var formState by mutableStateOf(SignInFormState())
        private set

    //MVI: 1 varivael de estado e 1 funcao que altera o estado

    fun onFormEvent(event: SignInFormEvent) {
        when (event) {
            is SignInFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
            }

            is SignInFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
            }
            SignInFormEvent.Submit -> doSignIn()
        }
    }

    private fun doSignIn() {
        formState = formState.copy(isLoading = true)
    }

}