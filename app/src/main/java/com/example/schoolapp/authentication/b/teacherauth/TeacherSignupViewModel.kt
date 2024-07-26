package com.example.schoolapp.com.example.schoolapp.authentication.b.teacherauth

import com.example.schoolapp.com.example.schoolapp.FirebaseUtil
import com.example.schoolapp.com.example.schoolapp.authentication.b.BaseAuth.BaseAuthViewModel
import kotlinx.coroutines.tasks.await

class TeacherSignupViewModel(): BaseAuthViewModel( "Teacher") {


   private val auth = FirebaseUtil.getAuth()
    private val db =  FirebaseUtil.getFireStoreDb()
    override fun validateUserInput(): Boolean {
        // Implement validation checks specific to student login (e.g., student ID check)
        return email.value.isNotBlank() && password.value.isNotBlank()
    }

     suspend fun performAuthAction(email:String, password:String,name:String): Boolean {
        val email =email
        val password=password

        try {
            val teacherRef = db.collection("Teachers").whereEqualTo("email", email)
            val querySnapshot = teacherRef.get().await()
            if (querySnapshot.isEmpty) {
                // User doesn't exist, create a new user
                val authResult = auth.createUserWithEmailAndPassword(email, password).await()
                val teacherId = authResult.user?.uid


                val teacherData = hashMapOf(
                    "email" to email,
                    "name" to name,
                    "schoolId" to "SchoolId"
                )
if(teacherId!=null) {
    db.collection("Teachers").document(teacherId).set(teacherData)
        .addOnSuccessListener {
            // Teacher created successfully

        }
        .addOnFailureListener {
            // Handle error

        }




}

else {
    // teacherid is null

}
            }
            else {
                // User already exists, handle error

            }

        }
        catch (e: Exception) {
            // Handle error
        }



        return false
    }

}