import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ActivateScreen() {
    var isUsingMobile by remember { mutableStateOf(true) } // State to toggle between mobile and email
    var inputValue by remember { mutableStateOf(TextFieldValue("")) } // State to store the input value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE7F3FF)), // Light blue background
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Studybase",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4285F4), // Blue text color
            modifier = Modifier.padding(top = 86.dp, bottom = 32.dp)
        )

        Spacer(modifier = Modifier.height(80.dp))

        val fontSize = if (inputValue.text.isEmpty()) 16.sp else 20.sp

        // Conditional OutlinedTextField based on the isUsingMobile state
        OutlinedTextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            label = { Text(if (isUsingMobile) "+91 Mobile" else "Email") },
            placeholder = { Text(if (isUsingMobile) "Mobile Number" else "Email Address") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = if (isUsingMobile) KeyboardType.Phone else KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            textStyle = TextStyle(color = Color.Black, fontSize = fontSize)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.End
        ) {
            // Toggle between "Use Email" and "Use Mobile"
            TextButton(onClick = {
                // Clear the input value when switching
                inputValue = TextFieldValue("")
                isUsingMobile = !isUsingMobile
            }) {
                Text(
                    text = if (isUsingMobile) "Use Email" else "Use Mobile",
                    color = Color(0xFF4285F4)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Submit your mobile number or email and we'll send you a code. Mobile number or email should be registered with the school.",
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* Next clicked */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(text = "Next")
        }

        Spacer(modifier = Modifier.weight(1f))

        TextButton(onClick = { /* Need Help clicked */ }) {
            Text(text = "Need Help?", color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewActivateScreen() {
    ActivateScreen()
}
