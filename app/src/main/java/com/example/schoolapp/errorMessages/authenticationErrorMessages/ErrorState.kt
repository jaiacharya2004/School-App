package com.example.schoolapp.errorMessages.authenticationErrorMessages

sealed class ErrorState {
    data class NetworkError(val message: String) : ErrorState()
    data class AuthenticationError(val message: String) : ErrorState()
    data class ValidationError(val message: String) : ErrorState()
    object EmptyFieldError : ErrorState()
    object UnknownError : ErrorState()
}
