package com.example.schoolapp.authentication.b.teacherauth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.schoolapp.com.example.schoolapp.FirebaseUtil

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class TeacherLoginViewModel(navController:NavController): ViewModel() {

    private val auth = FirebaseUtil.getAuthCustom()
    private val db = FirebaseUtil.getFireStoreDbCustom()


    fun performAuthAction(name : String, email: String, password: String) {
        val name = name
        val email = email
        val password = password

        viewModelScope.launch(Dispatchers.IO) {

            try {

                val teacherRef = db.collection("Teachers").whereEqualTo("Email", email)
                Log.d("m0","database access")
                val querySnapshot = teacherRef.get().await()
                Log.d("m3","$querySnapshot")

                if (querySnapshot.size()!=0) {
                    // User exist login
                    val authResult = auth.signInWithEmailAndPassword(email,password).await()
val user = auth.currentUser
                    Log.d("LOGIN", "User LoggedIn WITH user id ${user?.uid}")
                //  navigate to teacher home screen
                }
                else {
                    // User does not exists, handle error
                    Log.d("m1","User does not exists, bad credentials permission denied")

                }


//



            } catch (e: Exception) {
                // Handle error
            }


        }
    }

}