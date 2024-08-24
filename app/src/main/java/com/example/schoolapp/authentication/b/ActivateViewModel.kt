package com.example.schoolapp.authentication.b

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.room.util.copy
import com.example.schoolapp.appCentral.User
import com.example.schoolapp.appCentral.UserType


import com.example.schoolapp.com.example.schoolapp.FirebaseUtil
import com.example.schoolapp.navigation.Route
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.IOException





class ActivateViewModel(navController: NavController): ViewModel() {

    val navController = navController
    private val db = FirebaseUtil.getFireStoreDbCustom()


    private val _user = MutableStateFlow(User(
        docId = "null",
        name = "null",
        email = "null",
        userType = UserType.TEACHER
    ))

    val user: StateFlow<User> get() = _user




     @SuppressLint("SuspiciousIndentation")
     fun searchUserWithEmail(email:String, userType: UserType) {
         Log.d("functioncalled","documentSnapshot")


        viewModelScope.launch(Dispatchers.IO) {


            try {
                val documentSnapshot = userExistsOrNot(email,userType)
                                

                 /* What data should be there in case of
                 TEACHER -> Name, email (!fetch), ClassID,link reference for photo,
                 Whenever a class is generated we will create a unique ID for every class with SchoolName and number and other important stuff
                  */

                if (documentSnapshot != null) {
                    Log.d("DocSize","${documentSnapshot.get("Name")}")
                    _user.value = instantiateUser(documentSnapshot, userType)

                    // Navigate to NewScreen when user is found and instantiated
                    navController.navigate(Route.NewScreen.name)
                } else {
                    // Handle the case where no user was found
                    Log.d("UserNotFound", "No user found with email: $email")
                }



            }
            catch (e: Exception) {
                when (e) {
                    is FirebaseFirestoreException -> {
                    Log.e("FireStoreError", "Error saving data: ${e.message}")
                    }

                    is IOException -> {
                        Log.e("IOException", "ioexception: ${e.message}")

                    }
                    else -> {

                    }
                }
            }
        }
    }


    private suspend fun userExistsOrNot(email:String,userType: UserType):DocumentSnapshot?{
// this function should return a docSnapShot
        val collectionName:String = when(userType){
            UserType.TEACHER -> "Teachers"
            UserType.SCHOOL_HEAD -> "Schools"
        }
        val documentReference = db.collection(collectionName).whereEqualTo("Email",email).get().await()
Log.d("doc","${documentReference.size()}")
        return if (documentReference.documents.isNotEmpty()) {
            Log.d("Empty","No")
            documentReference.documents[0]
        } else {
            null
        }

    }

    private suspend fun activateUser(email:String,password:String)
    {
    /*
    Navigate to Password Screen
     */
    }

     private fun instantiateUser(documentSnapshot: DocumentSnapshot, userType: UserType):User{

         return User(
             docId = documentSnapshot.id,
             name = documentSnapshot.get("Name").toString(),
             email = documentSnapshot.get("Email").toString(),
             userType = userType
         )



        Log.d("Data","$user.email")

    }







}


