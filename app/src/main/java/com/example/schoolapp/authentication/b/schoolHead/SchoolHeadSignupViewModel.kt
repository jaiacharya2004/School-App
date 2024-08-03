package com.example.schoolapp.authentication.b.schoolHead

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.schoolapp.com.example.schoolapp.FirebaseUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SchoolHeadSignupViewModel(private val navController: NavController):ViewModel() {



        private val auth = FirebaseUtil.getAuthCustom()
        private val db = FirebaseUtil.getFireStoreDbCustom()

    fun performAuthActionSignup(email: String, password: String, name: String) {
            val name = name
            val email = email
            val password = password

            viewModelScope.launch(Dispatchers.IO) {

                try {
                    val schoolHeadRef = db.collection("Schools").whereEqualTo("Email", email)
                    Log.d("m0","database access")
                    val querySnapshot = schoolHeadRef.get().await()
                    Log.d("m1","$querySnapshot")
                    if (querySnapshot.isEmpty) {
                        // User doesn't exist, create a new user
                        val authResult = auth.createUserWithEmailAndPassword(email, password).await()
                        val schoolId = authResult.user?.uid


                        val schoolData = hashMapOf(
                            "Email" to email,
                        )
                        if (schoolId != null) {
                            db.collection("Schools").document(schoolId).set(schoolData)

                                .addOnSuccessListener {
                                    // Teacher created successfully
                                    navController.navigate("principal_home")
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



