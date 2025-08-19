package br.com.ada.droidchat.ui.validator

object PasswordValidator {

    fun isValid(value: String): Boolean {
        return true
//        return value.length >= 8 &&
//                value.any { it.isUpperCase() } &&
//                value.any { it.isLowerCase() } &&
//                value.any { it.isDigit() } &&
//                value.any { !it.isLetterOrDigit() }
    }

}