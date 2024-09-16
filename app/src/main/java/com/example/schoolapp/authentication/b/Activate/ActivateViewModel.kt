package com.example.schoolapp.authentication.b.Activate


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.ViewModel

import com.example.schoolapp.appCentral.UserType
import com.example.schoolapp.authentication.b.data.AuthRepositoryImpl
import com.example.schoolapp.authentication.b.model.Response
import com.example.schoolapp.authentication.b.model.Response.Loading
import com.example.schoolapp.authentication.b.repository.SignInResponse
import com.example.schoolapp.authentication.b.model.Response.Failure
import com.example.schoolapp.authentication.b.model.Response.Success








class ActivateViewModel(): ViewModel() {

     var userEmail:String =""



    private val authRepository = AuthRepositoryImpl()
    var userPresenceStatus = mutableStateOf<SignInResponse>(Failure(Exception("User Does not exists")))
    private set

    var userCreationStatus = mutableStateOf<SignInResponse>(Failure(Exception("Cannot create a new user:Error ")))
    private set

    var userSignInResponse = mutableStateOf<SignInResponse>(Success(false))
        private set

    suspend fun checkUserPresenceAsActivated(email:String):SignInResponse{

            Log.d("checkUserPresence in viewmodel 0","{${userPresenceStatus.value}}")

            userPresenceStatus.value = Loading

            userPresenceStatus.value = authRepository.checkUserPresenceAsActivated(email)

            Log.d("checkUserPresence in viewmodel 1","{${userPresenceStatus.value}}")
            if(userPresenceStatus.value is Success){
                userEmail = email
            }
        return userPresenceStatus.value
    }



   @SuppressLint("SuspiciousIndentation")
   suspend  fun confirmPassword(password:String):SignInResponse{

       Log.d("userCreationStatus in viewmodel 0","{${userCreationStatus.value}}")
       Log.d("email in viewmodel ","{${userEmail}}")

       userCreationStatus.value = Loading
       userCreationStatus.value = authRepository.firebaseSignUpWithEmailAndPassword(userEmail,password)

         Log.d("userCreationStatus in viewmodel 1","{${userCreationStatus.value}}")

    return userCreationStatus.value
    }


    suspend fun activateUser(){
       Log.d("docId","$userPresenceStatus")
        authRepository.markUserAsActivated()
    }

    suspend  fun logInWithEmailAndPassword(email: String, password: String) :SignInResponse {
        userSignInResponse.value = Loading
        userSignInResponse.value = authRepository.firebaseSignInWithEmailAndPassword(email, password)
        return userSignInResponse.value
    }


    suspend fun sendPasswordResetEmail(email:String): Response<Boolean> {
        return authRepository.sendPasswordResetEmail(email)

    }


}








