package com.example.schoolapp.com.example.schoolapp

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object FirebaseUtil {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    private val fireStoreDb: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() } // Lazy initialization
    fun getAuth(): FirebaseAuth {
        return auth
    }

    fun getFireStoreDb():FirebaseFirestore{
        return fireStoreDb
    }

}