package com.example.schoolapp.authentication.b.forget_password

import androidx.lifecycle.ViewModel
import com.example.schoolapp.authentication.b.data.AuthRepositoryImpl
import com.example.schoolapp.authentication.b.model.Response

class ForgetPassViewModel:ViewModel() {

    private val repo = AuthRepositoryImpl()

    suspend fun sendPasswordResetEmail(email:String): Response<Boolean> {
        return repo.sendPasswordResetEmail(email)

    }

}