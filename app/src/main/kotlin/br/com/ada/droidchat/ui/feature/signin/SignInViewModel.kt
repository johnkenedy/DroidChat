package br.com.ada.droidchat.ui.feature.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ada.droidchat.R
import br.com.ada.droidchat.data.repository.AuthRepository
import br.com.ada.droidchat.model.NetworkException
import br.com.ada.droidchat.model.Token
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var formState by mutableStateOf(SignInFormState())
        private set

    private val _signInActionFlow = MutableSharedFlow<SignInAction>()
    val signInAction = _signInActionFlow.asSharedFlow()

    //MVI: 1 variavel de estado e 1 funcao que altera o estado

    fun onFormEvent(event: SignInFormEvent) {
        when (event) {
            is SignInFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email, emailError = null)
            }

            is SignInFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password, passwordError = null)
            }

            SignInFormEvent.Submit -> {
                doSignIn(email = formState.email, password = formState.password)
            }
        }
    }

    private fun doSignIn(email: String, password: String) {
        var isFormValid = true
//        resetErrorFormState()
        if (email.isBlank()) {
            formState = formState.copy(emailError = R.string.error_message_email_invalid)
            isFormValid = false
        }

        if (password.isBlank()) {
            formState = formState.copy(passwordError = R.string.error_message_password_invalid)
            isFormValid = false
        }

        if (isFormValid) {
            viewModelScope.launch {
                formState = formState.copy(isLoading = true)
                authRepository.signIn(email, password).fold(
                    onSuccess = {
                        formState = formState.copy(isLoading = false)
                        _signInActionFlow.emit(SignInAction.Success)
                    },
                    onFailure = {
                        formState = formState.copy(isLoading = false)

                        val error =
                            if (it is NetworkException.ApiException && it.statusCode == 401) {
                                SignInAction.Error.UnauthorizedError
                            } else {
                                SignInAction.Error.Generic
                            }

                        _signInActionFlow.emit(error)


//                        State approach
//                        formState = formState.copy(
//                            isLoading = false,
//                            apiErrorMessageResId = if (it is NetworkException.ApiException) {
//                                when (it.statusCode) {
//                                    400 -> R.string.error_message_api_form_validation_failed
//                                    401 -> R.string.error_message_invalid_credentials
//                                    else -> R.string.common_generic_error_message
//                                }
//                            } else R.string.common_generic_error_message
//                        )
                    }
                )
            }
        }
    }

//    private fun resetErrorFormState() {
//        formState = formState.copy(
//            emailError = null,
//            passwordError = null,
//        )
//    }

    sealed interface SignInAction {
        data object Success : SignInAction
        sealed interface Error : SignInAction {
            data object Generic : Error
            data object UnauthorizedError : Error
        }
    }

}