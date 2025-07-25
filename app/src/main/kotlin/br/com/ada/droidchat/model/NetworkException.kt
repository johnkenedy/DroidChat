package br.com.ada.droidchat.model

sealed class NetworkException(message: String, cause: Throwable? = null) : Exception(message, cause) {
    class ApiException(val responseMessage: String, val statusCode: Int) : NetworkException(responseMessage)
    class UnknowNetworkException(cause: Throwable? = null) : NetworkException("Unknown network exception", cause)
}