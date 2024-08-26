package com.example.schoolapp.studybase.Activate

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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
//import com.example.schoolapp.appCentral.UserType
import com.example.schoolapp.authentication.b.ActivateViewModel



@Composable
fun ActivateScreen(navController: NavController) {
    val navController = navController
    val emailState = remember { mutableStateOf("") }
    val viewModel = remember{ ActivateViewModel(navController) }
    var isExpanded by remember { mutableStateOf(false) }
    // State to manage the selected option
    var selectedOption by remember { mutableStateOf("Select an option") }


    Column(
        modifier = Modifier
            .fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            text = "Studybase",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4285F4), // Blue text color
            modifier = Modifier.padding(top = 86.dp, bottom = 32.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))


        // Conditional OutlinedTextField based on the isUsingMobile state
        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text(text = "Email")},
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 17.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType =  KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp)
        )

        Spacer(modifier = Modifier.height(16.dp))



        Text(
            text = "Submit your email and we'll send you a code. Email should be registered with the school.",
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))



        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { isExpanded = !isExpanded },
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(Color.White),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Card Header
                Text(text = "Select User Type")

                // Expandable content
                if (isExpanded) {
                    Column(modifier = Modifier.padding(top = 16.dp)
                        .clip(RoundedCornerShape(18.dp))
                    ) {

                        // Box for "School" RadioButton
                        Box(
                            modifier = Modifier
                                .height(56.dp)
                                .background(
                                    if (selectedOption == "School") Color(0xFFBB86FC) else Color.White
                                )
                                .clip(RoundedCornerShape(18.dp))
                                .clickable { selectedOption = "School" }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 16.dp)
                                    .clip(RoundedCornerShape(18.dp))
                            ) {
                                Text(text = "School")
                                RadioButton(
                                    selected = selectedOption == "School",
                                    onClick = { selectedOption = "School" }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Box for "Teacher" RadioButton
                        Box(
                            modifier = Modifier
                                .height(56.dp)
                                .background(
                                    if (selectedOption == "Teacher") Color(0xFFBB86FC) else Color.White
                                )
                                .clip(RoundedCornerShape(18.dp))
                                .clickable { selectedOption = "Teacher" }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,

                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 16.dp)
                                    .clip(RoundedCornerShape(18.dp))
                            ) {
                                Text(text = "Teacher")
                                RadioButton(
                                    selected = selectedOption == "Teacher",
                                    onClick = { selectedOption = "Teacher" }
                                )
                            }
                        }
                    }
                }
            }
        }




        Button(
            onClick = {
            navController.navigate("activate_password")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)

        ) {
            Text(text = "Next")
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {
            navController.navigate("login_screen")
        }) {
            Text(
                text = "Already have an account? Login",

            )
        }
    }
}
