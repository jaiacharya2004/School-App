package com.example.schoolapp.authentication.b.teacherauth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.schoolapp.com.example.schoolapp.FirebaseUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

 class TeacherSignupViewModel(navController:NavController): ViewModel() {


     private val auth = FirebaseUtil.getAuthCustom()
     private val db = FirebaseUtil.getFireStoreDbCustom()
//     fun validateUserInput(): Boolean {
//        // Implement validation checks specific to student login (e.g., student ID check)
//
//    }

     fun performAuthAction(email: String, password: String) {
         val email = email
         val password = password

         viewModelScope.launch(Dispatchers.IO) {

             try {
                 val teacherRef = db.collection("Teachers").whereEqualTo("Email", email)
                 Log.d("m0","database access")
                 val querySnapshot = teacherRef.get().await()
                 Log.d("m3","$querySnapshot")
                 if (querySnapshot.isEmpty) {
                     // User doesn't exist, create a new user
                     val authResult = auth.createUserWithEmailAndPassword(email, password).await()
                     val teacherId = authResult.user?.uid


                     val teacherData = hashMapOf(
                         "Email" to email,


                     )
                     if (teacherId != null) {
                         db.collection("Teachers").document(teacherId).set(teacherData)
                             .addOnSuccessListener {
                                 // Teacher created successfully

                             }
                             .addOnFailureListener {
                                 // Handle error

                             }
// if new user4 is created navigate to teacher home screen

                     } else {
                         // teacherid is null

                     }
                 } else {
                     // User already exists, handle error
                     Log.d("m1","User exists")

                 }

             } catch (e: Exception) {
                 // Handle error
             }


         }
     }

 }