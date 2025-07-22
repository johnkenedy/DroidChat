package br.com.ada.droidchat.data.repository

import br.com.ada.droidchat.model.CreateAccount

interface AuthRepository {

    suspend fun signUp(createAccount: CreateAccount)

    suspend fun signIn(username: String, password: String)

}