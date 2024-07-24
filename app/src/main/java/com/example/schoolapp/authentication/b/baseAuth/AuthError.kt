package com.example.schoolapp.com.example.schoolapp.authentication.b.baseAuth

sealed class AuthError {

    object WrongCredentialsError : AuthError()
    object NetworkError : AuthError()

}