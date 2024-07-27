package com.example.schoolapp.com.example.schoolapp

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object FirebaseUtil {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    private val fireStoreDb: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() } // Lazy initialization
    fun getAuthCustom(): FirebaseAuth {
        return auth
    }

    fun getFireStoreDbCustom():FirebaseFirestore{
        return fireStoreDb
    }

}