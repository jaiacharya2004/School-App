package com.example.schoolapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import android.util.Log

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _SchoolAuthState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _SchoolAuthState

    private val _studentauthState = MutableLiveData<AuthState>()
    val StudentAuthState: LiveData<AuthState> = _studentauthState

    private val _teacherauthState = MutableLiveData<AuthState>()
    val TeacherAuthState: LiveData<AuthState> = _teacherauthState


    init {
        checkAuthStatus()
    }

    fun checkAuthStatus() {
        if (auth.currentUser == null) {
            _SchoolAuthState.value = AuthState.Unauthenticated
        } else {
            _SchoolAuthState.value = AuthState.Authenticated
        }
    }

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _SchoolAuthState.value = AuthState.Error("Email or Password can't be empty")
            return
        }

        _SchoolAuthState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("AuthViewModel", "Login successful")
                    _SchoolAuthState.value = AuthState.Authenticated
                } else {
                    Log.d("AuthViewModel", "Login failed: ${task.exception?.message}")
                    _SchoolAuthState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signup(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _SchoolAuthState.value = AuthState.Error("Email or Password can't be empty")
            return
        }

        _SchoolAuthState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("AuthViewModel", "Signup successful")
                    _SchoolAuthState.value = AuthState.Authenticated
                } else {
                    Log.d("AuthViewModel", "Signup failed: ${task.exception?.message}")
                    _SchoolAuthState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signout() {
        auth.signOut()
        _SchoolAuthState.value = AuthState.Unauthenticated
    }
}

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}
