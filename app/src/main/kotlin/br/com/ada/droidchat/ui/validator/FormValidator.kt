package br.com.ada.droidchat.ui.validator

interface FormValidator<FormState> {
    fun validate(formState: FormState): FormState
}