package com.example.schoolapp.authentication.b.Activate

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.schoolapp.R
import com.example.schoolapp.appCentral.User
import com.example.schoolapp.appCentral.UserType
import com.example.schoolapp.authentication.b.authErrors.ErrorHandler
import com.example.schoolapp.authentication.b.authErrors.ErrorViewModel
import com.example.schoolapp.authentication.b.model.Response.Loading
import com.example.schoolapp.authentication.b.model.Response.Failure
import com.example.schoolapp.authentication.b.model.Response.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// user type selection is not done as per user selection on screen it is set to default teacher
// if email format is not correct don't check in the database implement an approach for that
// we are getting two time the log message at line number 215 - 221 may be its due to viewModelScope
@SuppressLint("SuspiciousIndentation")
@Composable
fun ActivateScreen(navController: NavController,activateViewModel: ActivateViewModel ) {
   val errorViewModel = ErrorViewModel()
    var emailState by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("Teacher") } // Preselect "Teacher"
    var showError by remember { mutableStateOf(false) } // Track whether to show the error message
    val errorState by errorViewModel.errorState.collectAsState()

    var isLoading by remember { mutableStateOf(false) }
    var userPresenceStatus= activateViewModel.userPresenceStatus
    var buttonClickCount = remember { mutableIntStateOf(0) }
    var user = User(
         email = "",
         docId = "",
         userType = UserType.TEACHER
     )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF6A1B9A), // Purple color
                        Color.White
                    )
                )
            )
    ) {
        Row(
            modifier = Modifier.padding(start = 100.dp, top = 70.dp)
        ) {
            Text(
                text = "StudyBase",
                color = Color.White,
                fontSize = 36.sp,
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 216.dp, start = 20.dp, end = 20.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(24.dp),
        ) {

//            ErrorHandler(
//                errorState = errorState,
//                onDismiss = { errorViewModel.clearError() }
//            )


            Column(
                modifier = Modifier.padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Activate your Account",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4285F4),
                    modifier = Modifier.padding(bottom = 22.dp)
                )

                // Display error message if needed


                OutlinedTextField(
                    value = emailState,
                    onValueChange = {
                        emailState = it
                        showError = false // Hide the error message when user starts typing
                    },
                    label = { Text("Email") },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black,
                        cursorColor = Color.Black,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 17.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    textStyle = TextStyle(color = Color.Black, fontSize = 18.sp)
                )
                if (showError) {
                    Text(
                        text = "Please enter your email.",
                        color = Color.Red,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(22.dp))

                Text(
                    text = "Select User Type",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    UserTypeCard(
                        title = "School",
                        isSelected = selectedOption == "School",
                        onClick = { selectedOption = "School" }
                    )
                    UserTypeCard(
                        title = "Teacher",
                        isSelected = selectedOption == "Teacher",
                        onClick = { selectedOption = "Teacher" }
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {

                        Log.d("buttonClickCount", "$buttonClickCount")
                        if (emailState.isEmpty()) {
                            showError = true
                        } else {
                            buttonClickCount.intValue++
                            isLoading = true
                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                ) {
                    Text(text = "Next", fontSize = 18.sp)

                }
if(buttonClickCount.intValue>0) {
    LaunchedEffect(key1 = buttonClickCount) { // Key based on email change

            if (emailState.isNotEmpty()) {

                userPresenceStatus.value =
                    activateViewModel.checkUserPresenceAsActivated(emailState)

                when (userPresenceStatus.value) {
                    is Success -> {
                        // instantiate the user with docId at higher state
                        activateViewModel.userEmail = emailState
                        Log.d("Email", "${activateViewModel.userEmail}")
                        navController.navigate("activate_password")

                    }

                    is Failure -> {
                        Log.d("userPresenceStatus", "$userPresenceStatus.value.toString()")

                    }

                    is Loading -> {
                        Log.d("userPresenceStatus", "$userPresenceStatus.value.toString()")

                    }

                }

            } else {
                isLoading = false // Reset loading state if email is empty
            }

    }
}


                // creating a user object and storing the email if is Success and then accessing the user object in the next screen which is activate password


                Spacer(modifier = Modifier.height(16.dp))

                TextButton(onClick = { navController.navigate("login_screen") }) {
                    Text(text = "Already have an account? Login")
                }
                ErrorHandler(errorState = errorState) {
                    errorViewModel.clearError()
                }
            }
        }
    }
}





@Composable
fun UserTypeCard(title: String, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(120.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFF6A1B9A) else Color.White
        ),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            Image(
               // resources required for the image
                painter = painterResource(id = if (title == "School") R.drawable.school_bg
                else R.drawable.school_bg),
                contentDescription = "$title Icon",
//                modifier = Modifier.size(80.dp) // Adjust the size as needed
            )

            Spacer(modifier = Modifier.height(8.dp)) // Space between the image and text

            Text(
                text = title,
                color = if (isSelected) Color.White else Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

