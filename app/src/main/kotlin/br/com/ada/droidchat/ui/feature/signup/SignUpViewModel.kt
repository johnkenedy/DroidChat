package br.com.ada.droidchat.ui.feature.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.ada.droidchat.R

class SignUpViewModel : ViewModel() {

    var formState by mutableStateOf(SignUpFormState())
        private set

    fun onFormEvent(event: SignUpFormEvent) {
        when (event) {
            is SignUpFormEvent.ProfilePhotoUriChanged -> {
                formState = formState.copy(profilePictureUri = event.uri)
            }

            is SignUpFormEvent.FirstNameChanged -> {
                formState = formState.copy(firstName = event.firstName)
            }

            is SignUpFormEvent.LastNameChanged -> {
                formState = formState.copy(lastName = event.lastName)
            }

            is SignUpFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
            }

            is SignUpFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
                updatePasswordExtraText()
            }

            is SignUpFormEvent.ConfirmPasswordChanged -> {
                formState = formState.copy(confirmPassword = event.confirmPassword)
                updatePasswordExtraText()
            }

            SignUpFormEvent.OpenProfilePictureModal -> {
                formState = formState.copy(isProfilePictureModalOpen = true)
            }

            SignUpFormEvent.CloseProfilePictureModal -> {
                formState = formState.copy(isProfilePictureModalOpen = false)
            }

            SignUpFormEvent.Submit -> {
                doSignUp()
            }
        }
    }

    private fun updatePasswordExtraText() {
        formState = formState.copy(
            passwordExtraText =
                if (formState.password.isNotEmpty() && formState.password == formState.confirmPassword) {
                    R.string.feature_sign_up_passwords_match
                } else null
        )
    }

    private fun isValidForm(): Boolean {
        return false
    }

    private fun doSignUp() {
        if (isValidForm()) {
            formState = formState.copy(isLoading = true)
            //request to api
        }

    }

}