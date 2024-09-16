package com.example.schoolapp.authentication.b.repository

import com.example.schoolapp.appCentral.User
import com.example.schoolapp.appCentral.UserType
import com.example.schoolapp.authentication.b.model.Response
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow


typealias SignUpResponse = Response<Any>
typealias SendEmailVerificationResponse = Response<Boolean>
typealias SignInResponse = Response<Any>
typealias ReloadUserResponse = Response<Boolean>
typealias SendPasswordResetEmailResponse = Response<Boolean>
typealias RevokeAccessResponse = Response<Boolean>
typealias AuthStateResponse = StateFlow<Boolean>
typealias UserExistsResponse =  Response<Any>
typealias UserActivatedResponse = Response<Boolean>

interface AuthRepository {
    val currentUser: FirebaseUser?

    var docId:String

    suspend fun firebaseSignUpWithEmailAndPassword(email: String, password: String): SignUpResponse

    suspend fun sendEmailVerification(): SendEmailVerificationResponse

    suspend fun firebaseSignInWithEmailAndPassword(email: String, password: String): SignInResponse

    suspend fun reloadFirebaseUser(): ReloadUserResponse

    suspend fun sendPasswordResetEmail(email: String): SendPasswordResetEmailResponse

    fun signOut()

    suspend fun revokeAccess(): RevokeAccessResponse

    fun getAuthState(viewModelScope: CoroutineScope): AuthStateResponse

    suspend fun checkUserPresenceAsActivated(email:String):UserExistsResponse

    suspend fun markUserAsActivated():Any
}