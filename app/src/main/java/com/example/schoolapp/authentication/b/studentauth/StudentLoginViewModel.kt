package com.example.schoolapp.authentication.b.studentauth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolapp.com.example.schoolapp.FirebaseUtil
import com.example.schoolapp.errorMessages.authenticationErrorMessages.ErrorState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class StudentLoginViewModel : ViewModel() {

    private val auth = FirebaseUtil.getAuthCustom()
    private val db = FirebaseUtil.getFireStoreDbCustom()

    private val _errorState = MutableStateFlow<ErrorState?>(null)
    val errorState: StateFlow<ErrorState?> get() = _errorState

    fun login(email: String, password: String) {
        if (email.isEmpty()) {
            _errorState.value = ErrorState.ValidationError("Email cannot be empty")
            return
        }

        if (password.isEmpty()) {
            _errorState.value = ErrorState.ValidationError("Password cannot be empty")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val studentRef = db.collection("Students").whereEqualTo("Email", email)
                val querySnapshot = studentRef.get().await()

                if (querySnapshot.isEmpty) {
                    _errorState.value = ErrorState.AuthenticationError("User does not exist")
                } else {
                    // User exists, proceed with login
                    val authResult = auth.signInWithEmailAndPassword(email, password).await()
                    val user = auth.currentUser
                    // Handle successful login, e.g., navigate to home screen
                    _errorState.value = null
                }
            } catch (e: Exception) {
                _errorState.value = ErrorState.NetworkError("Failed to login: ${e.message}")
            }
        }
    }

    fun clearError() {
        _errorState.value = null
    }
}
