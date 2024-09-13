package com.example.schoolapp.authentication.b.authErrors

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ErrorViewModel : ViewModel() {
    private val _errorState = MutableStateFlow<ErrorState>(ErrorState.UnknownError)
    val errorState: StateFlow<ErrorState> = _errorState

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun handleError(error: ErrorState) {
        _errorState.value = error
    }

    fun clearError() {
        _errorState.value = ErrorState.UnknownError
    }

    fun checkEmailExists(email: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val userExists = doesUserExist(email)
                onResult(userExists)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }

    private suspend fun doesUserExist(email: String): Boolean {
        // Replace this with your Firestore logic if using Firestore
        val result = auth.fetchSignInMethodsForEmail(email).await()
        return result.signInMethods?.isNotEmpty() == true
    }
}