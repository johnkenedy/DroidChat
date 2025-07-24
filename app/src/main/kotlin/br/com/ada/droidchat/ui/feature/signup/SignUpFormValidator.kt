package br.com.ada.droidchat.ui.feature.signup

import br.com.ada.droidchat.R
import br.com.ada.droidchat.ui.validator.EmailValidator
import br.com.ada.droidchat.ui.validator.FormValidator
import br.com.ada.droidchat.ui.validator.PasswordValidator
import javax.inject.Inject

class SignUpFormValidator @Inject constructor() : FormValidator<SignUpFormState> {

    override fun validate(formState: SignUpFormState): SignUpFormState {
        val isFirstNameValid = formState.firstName.isNotEmpty() && !formState.firstName.any { it.isDigit() } && formState.firstName.length > 2
        val isLastNameValid = formState.lastName.isNotEmpty() && !formState.lastName.any { it.isDigit() } && formState.lastName.length > 2
        val isEmailValid = EmailValidator.isValid(formState.email)
        val isPasswordValid = PasswordValidator.isValid(formState.password)
        val isConfirmPasswordValid = PasswordValidator.isValid(formState.confirmPassword)
                && formState.password == formState.confirmPassword
        val hasError = listOf(
            isFirstNameValid,
            isLastNameValid,
            isEmailValid,
            isPasswordValid,
            isConfirmPasswordValid
        ).any { !it }

        return formState.copy(
            firstNameError = if (!isFirstNameValid) R.string.error_message_field_blank else null,
            lastNameError = if (!isLastNameValid) R.string.error_message_field_blank else null,
            emailError = if (!isEmailValid) R.string.error_message_email_invalid else null,
            passwordError = if (!isPasswordValid) R.string.error_message_password_invalid else null,
            confirmPasswordError = if (!isConfirmPasswordValid) R.string.error_message_password_confirmation_invalid else null,
            hasError = hasError,
        )
    }

}