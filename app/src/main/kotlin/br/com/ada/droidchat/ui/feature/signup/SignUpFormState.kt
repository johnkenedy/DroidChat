package br.com.ada.droidchat.ui.feature.signup

import android.net.Uri
import androidx.annotation.StringRes

data class SignUpFormState(
    val profilePictureUri: Uri? = null,
    val firstName: String = "",
    @StringRes val firstNameError: Int? = null,
    val lastName: String = "",
    @StringRes val lastNameError: Int? = null,
    val email: String = "",
    @StringRes val emailError: Int? = null,
    val password: String = "",
    @StringRes val passwordError: Int? = null,
    val confirmPassword: String = "",
    @StringRes val confirmPasswordError: Int? = null,
    val isProfilePictureModalOpen: Boolean = false,
    val isLoading: Boolean = false,
)