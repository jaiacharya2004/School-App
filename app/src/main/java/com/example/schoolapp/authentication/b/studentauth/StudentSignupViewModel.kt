package com.example.schoolapp.authentication.b.studentauth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.schoolapp.com.example.schoolapp.FirebaseUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class StudentSignupViewModel(navController:NavController):ViewModel(){

        private val auth = FirebaseUtil.getAuthCustom()
        private val db = FirebaseUtil.getFireStoreDbCustom()
//     fun validateUserInput(): Boolean {
//        // Implement validation checks specific to student login (e.g., student ID check)
//
//    }

        fun performAuthActionSignup(email: String, password: String) {
            val email = email
            val password = password

            viewModelScope.launch(Dispatchers.IO) {

                try {
                    val studentRef = db.collection("Students").whereEqualTo("Email", email)
                    Log.d("m0","database access")
                    val querySnapshot = studentRef.get().await()
                    Log.d("m1","$querySnapshot")
                    if (querySnapshot.isEmpty) {
                        // User doesn't exist, create a new user
                        val authResult = auth.createUserWithEmailAndPassword(email, password).await()
                        val studentId = authResult.user?.uid


                        val studentData = hashMapOf(
                            "Email" to email,
                        )
                        if (studentId != null) {
                            db.collection("Students").document(studentId).set(studentData)
                                .addOnSuccessListener {
                                    // Teacher created successfully

                                }
                                .addOnFailureListener {
                                    // Handle error

                                }


                        } else {
                            // teacherid is null

                        }
                    } else {
                        // User already exists, handle error
                        Log.d("m2","User exists")

                    }

                } catch (e: Exception) {
                    // Handle error
                }


            }
        }

    }

