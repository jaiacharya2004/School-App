package com.example.schoolapp.AppCentral

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.remote.ConnectivityMonitor

object AppState {
//    val currentUser: MutableLiveData<User?> = MutableLiveData()
    val email: MutableLiveData<String?> = MutableLiveData()
    val isUserLoggedIn:Boolean = false
//    val authenticationStatus: AuthenticationStatus = AuthenticationStatus.Idle,
//    val networkStatus: NetworkStatus = NetworkStatus.Unknown,
//    val theme: Theme = Theme.Light,
//    val isLoading: Boolean = false,
//    val errorMessage: String? = null,
  //user preferences -> Themes, colors and notifications settings
    // OS type and Device specification like Screen size etc.
    // Network Status
    // ... other update methods
}


enum class AuthenticationStatus {
    Idle,
    Loading,
    Authenticated,
    Error
}

enum class NetworkStatus {
    Unknown,
    Connected,
    Disconnected
}