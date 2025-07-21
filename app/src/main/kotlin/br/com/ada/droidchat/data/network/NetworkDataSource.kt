package br.com.ada.droidchat.data.network

import br.com.ada.droidchat.data.network.model.AuthRequest
import br.com.ada.droidchat.data.network.model.CreateAccountRequest
import br.com.ada.droidchat.data.network.model.TokenResponse

interface NetworkDataSource {

    suspend fun signUp(request: CreateAccountRequest)

    suspend fun signIn(request: AuthRequest): TokenResponse
}