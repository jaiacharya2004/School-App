package com.example.schoolapp.authentication.b.teacherauth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolapp.com.example.schoolapp.FirebaseUtil
import com.example.schoolapp.errorMessages.authenticationErrorMessages.ErrorState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class TeacherSignupViewModel : ViewModel() {

    private val auth = FirebaseUtil.getAuthCustom()
    private val db = FirebaseUtil.getFireStoreDbCustom()

    private val _errorState = MutableStateFlow<ErrorState?>(null)
    val errorState: StateFlow<ErrorState?> get() = _errorState

    fun performSignup(name: String, email: String, password: String) {

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
                val authResult = auth.createUserWithEmailAndPassword(email, password).await()
                val user = authResult.user
                user?.let {
                    val teacher = mapOf("Name" to name, "Email" to email)
                    db.collection("Teachers").document(user.uid).set(teacher).await()
                    Log.d("TeacherSignup", "User Registered with user id ${user.uid}")
                    // Navigate to teacher home screen or handle success
                }
            } catch (e: Exception) {
                Log.e("TeacherSignup", "Signup failed", e)
                // Handle error appropriately, e.g., by updating the error view model
            }
        }
    }
}
