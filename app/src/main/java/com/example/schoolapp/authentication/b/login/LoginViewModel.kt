package com.example.schoolapp.authentication.b.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolapp.authentication.b.data.AuthRepositoryImpl
import com.example.schoolapp.authentication.b.model.Response.Loading
import com.example.schoolapp.authentication.b.model.Response.Success
import com.example.schoolapp.authentication.b.repository.AuthRepository
import com.example.schoolapp.authentication.b.repository.SignInResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject



class LoginViewModel @Inject constructor(

): ViewModel() {

    private val repo = AuthRepositoryImpl()
    var signInResponse = mutableStateOf<SignInResponse>(Success(false))
        private set

    fun logInWithEmailAndPassword(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("fc1","yes")
        signInResponse.value = Loading
        signInResponse.value = repo.firebaseSignInWithEmailAndPassword(email, password)
     // if sign in success the signInResponse will store the Success<uid>
    }

}