package com.example.schoolapp.authentication.b.schoolHead

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolapp.com.example.schoolapp.FirebaseUtil
import com.example.schoolapp.errorMessages.authenticationErrorMessages.ErrorState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SchoolHeadSignupViewModel : ViewModel() {

    private val auth = FirebaseUtil.getAuthCustom()
    private val db = FirebaseUtil.getFireStoreDbCustom()

    // Using StateFlow for error handling
    private val _errorState = MutableStateFlow<ErrorState?>(null)
    val errorState: StateFlow<ErrorState?> get() = _errorState

    // Function to handle signup logic
    fun performAuthAction(name: String, email: String, password: String) {
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
                val schoolHeadRef = db.collection("Schools").whereEqualTo("Email", email)
                val querySnapshot = schoolHeadRef.get().await()

                if (querySnapshot.isEmpty) {
                    val authResult = auth.createUserWithEmailAndPassword(email, password).await()
                    val schoolId = authResult.user?.uid

                    val schoolData = hashMapOf("Email" to email)
                    if (schoolId != null) {
                        db.collection("Schools").document(schoolId).set(schoolData).await()
                        _errorState.value = null // No errors, navigate to home
                    } else {
                        _errorState.value = ErrorState.UnknownError
                    }
                } else {
                    _errorState.value = ErrorState.AuthenticationError("User already exists")
                }
            } catch (e: Exception) {
                _errorState.value = ErrorState.NetworkError("Failed to create user: ${e.message}")
            }
        }
    }

    fun clearError() {
        _errorState.value = null
    }
}
