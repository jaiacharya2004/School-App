package com.example.schoolapp.authentication.b.teacherauth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolapp.com.example.schoolapp.FirebaseUtil
import com.example.schoolapp.errorMessages.authenticationErrorMessages.ErrorState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class TeacherLoginViewModel : ViewModel() {

    private val auth = FirebaseUtil.getAuthCustom()
    private val db = FirebaseUtil.getFireStoreDbCustom()

    // Using StateFlow for error handling
    private val _errorState = MutableStateFlow<ErrorState?>(null)
    val errorState: StateFlow<ErrorState?> get() = _errorState

    // Function to handle login logic
    fun performAuthAction(email: String, password: String) {
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
                val teacherRef = db.collection("Teachers").whereEqualTo("Email", email).get().await()

                if (teacherRef.isEmpty) {
                    _errorState.value = ErrorState.AuthenticationError("User does not exist")
                } else {
                    val authResult = auth.signInWithEmailAndPassword(email, password).await()
                    val user = auth.currentUser
                    if (user != null) {
                        _errorState.value = null // No errors, navigate to home
                        // Handle successful login and navigation
                    } else {
                        _errorState.value = ErrorState.UnknownError
                    }
                }
            } catch (e: Exception) {
                _errorState.value = ErrorState.NetworkError("Login failed: ${e.message}")
            }
        }
    }

    fun clearError() {
        _errorState.value = null
    }
}
