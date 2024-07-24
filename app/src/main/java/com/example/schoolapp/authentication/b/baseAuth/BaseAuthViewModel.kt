package com.example.schoolapp.com.example.schoolapp.authentication.b.BaseAuth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.schoolapp.com.example.schoolapp.authentication.b.baseAuth.AuthError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseAuthViewModel( val userType: String):ViewModel() {

    val email = mutableStateOf("")
    val password = mutableStateOf("")


    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    abstract fun validateUserInput(): Boolean

    abstract suspend fun performAuthAction(): Boolean

    fun clearErrorMessage() {
        _errorMessage.value = null
    }





}