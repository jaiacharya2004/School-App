package com.example.schoolapp.authentication.b.data

import android.util.Log
import com.example.schoolapp.appCentral.UserType
import com.example.schoolapp.authentication.b.model.Response.Success
import com.example.schoolapp.authentication.b.model.Response.Failure

import com.example.schoolapp.authentication.b.repository.AuthRepository
import com.example.schoolapp.authentication.b.repository.UserExistsResponse
import com.example.schoolapp.com.example.schoolapp.FirebaseUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.sign

@Singleton
class AuthRepositoryImpl @Inject constructor(


) : AuthRepository {
    private val auth= FirebaseUtil.getAuthCustom()
    private val db = FirebaseUtil.getFireStoreDbCustom()
    override val currentUser get() = auth.currentUser
    override var docId: String ="NA"


    override suspend fun firebaseSignUpWithEmailAndPassword(
        email: String, password: String
    ) = try {
       val signUpResult = auth.createUserWithEmailAndPassword(email, password).await()
        Log.d("TAG ", "$signUpResult")
        val uid = signUpResult.user?.uid?:throw Exception("Unsuccessful SignUp operation")
        Success(uid)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun sendEmailVerification() = try {
        auth.currentUser?.sendEmailVerification()?.await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun firebaseSignInWithEmailAndPassword(
        email: String, password: String
    ) = try {
       val signInResult = auth.signInWithEmailAndPassword(email, password).await()
       val uid = signInResult.user?.uid?: throw Exception("Unsuccessful sign in operation")
        Success(uid)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun reloadFirebaseUser() = try {
        auth.currentUser?.reload()?.await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun sendPasswordResetEmail(email: String) = try {
        auth.sendPasswordResetEmail(email).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override fun signOut() = auth.signOut()

    override suspend fun revokeAccess() = try {
        auth.currentUser?.delete()?.await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override fun getAuthState(viewModelScope: CoroutineScope) = callbackFlow {
        val authStateListener = AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), auth.currentUser == null)

    override suspend fun checkUserPresence(email: String,userType: UserType)


        = try {

            val collectionRef = when (userType) {
                UserType.TEACHER -> db.collection("Teachers")
                UserType.SCHOOL_HEAD -> db.collection("Schools")
            }


            val querySnapshot = collectionRef.whereEqualTo("Email", email).get().await()

            if (querySnapshot.documents.isNotEmpty()) {

                val documentId = querySnapshot.documents.first().id
                docId = documentId

                Success(documentId)
            } else {

                Failure(Exception("User does not exist 1"))
            }
        } catch (e: Exception) {

            Failure(e)
        }

    override suspend fun markUserAsActivated( userType: UserType)
       =   try{
        val collectionRef = when (userType) {
            UserType.TEACHER -> db.collection("Teachers")
            UserType.SCHOOL_HEAD -> db.collection("Schools")
        }

        val activationStatus = hashMapOf("Activation Status" to true)

        val writeTask = collectionRef.document(docId).set(activationStatus, SetOptions.merge()).await()
       }
    catch (e:Exception){
        Failure(Exception("Unknown error: ${e.message}"))

    }
}