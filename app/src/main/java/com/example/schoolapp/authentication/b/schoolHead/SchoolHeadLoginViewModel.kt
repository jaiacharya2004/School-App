package com.example.schoolapp.authentication.b.schoolHead

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolapp.com.example.schoolapp.FirebaseUtil
import com.example.schoolapp.errorMessages.authenticationErrorMessages.ErrorState
import com.example.schoolapp.errorMessages.authenticationErrorMessages.ErrorViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SchoolHeadLoginViewModel(
    private val errorViewModel: ErrorViewModel
) : ViewModel() {

    private val auth = FirebaseUtil.getAuthCustom()
    private val db = FirebaseUtil.getFireStoreDbCustom()

    fun performAuthAction(name: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (email.isEmpty() || password.isEmpty()) {
                    errorViewModel.handleError(ErrorState.EmptyFieldError("Fields cannot be empty"))
                    return@launch
                }

                val schoolRef = db.collection("Students").whereEqualTo("Email", email)
                val querySnapshot = schoolRef.get().await()

                if (querySnapshot.size() != 0) {
                    // User exists, perform login
                    auth.signInWithEmailAndPassword(email, password).await()
                    val user = auth.currentUser
                    Log.d("LOGIN", "User LoggedIn WITH user id ${user?.uid}")
                    // Navigate to home screen or perform other actions as needed
                } else {
                    errorViewModel.handleError(ErrorState.AuthenticationError("User does not exist"))
                }
            } catch (e: Exception) {
                errorViewModel.handleError(ErrorState.NetworkError("Network error occurred"))
                Log.e("LOGIN_ERROR", "Error during authentication", e)
            }
        }
    }
}
