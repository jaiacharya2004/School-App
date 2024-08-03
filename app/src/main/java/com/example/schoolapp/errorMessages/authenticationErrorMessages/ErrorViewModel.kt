package com.example.schoolapp.errorMessages.authenticationErrorMessages

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ErrorViewModel : ViewModel() {

    var errorState = mutableStateOf<ErrorState>(ErrorState.UnknownError)
        private set

    fun handleError(error: ErrorState) {
        errorState.value = error
    }

    fun clearError() {
        errorState.value = ErrorState.UnknownError
    }
}
