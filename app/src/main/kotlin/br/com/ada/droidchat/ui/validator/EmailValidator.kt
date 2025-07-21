package br.com.ada.droidchat.ui.validator

object EmailValidator {
    private const val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"

    fun isValid(value: String): Boolean {
        return value.matches(EMAIL_REGEX.toRegex())
    }
}