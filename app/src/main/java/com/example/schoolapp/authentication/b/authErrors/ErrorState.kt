package com.example.schoolapp.authentication.b.authErrors

sealed class ErrorState {
    data class NetworkError(val message: String) : ErrorState()
    data class AuthenticationError(val message: String) : ErrorState()
    data class ValidationError(val message: String) : ErrorState()
    data class EmptyFieldError(val message: String) : ErrorState()
    object UnknownError : ErrorState()
}