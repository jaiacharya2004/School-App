package com.example.schoolapp.errorMessages.authenticationErrorMessages

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorHandler(
    errorState: ErrorState,
    onDismiss: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    var showSnackbar by remember { mutableStateOf(false) }

    LaunchedEffect(errorState) {
        val message = when (errorState) {
            is ErrorState.NetworkError -> errorState.message
            is ErrorState.AuthenticationError -> errorState.message
            is ErrorState.ValidationError -> errorState.message
            ErrorState.EmptyFieldError -> "Fields cannot be empty"
            else -> ""
        }

        showSnackbar = message.isNotEmpty()

        if (showSnackbar) {
            snackBarHostState.showSnackbar(message = message)
        }
    }

    SnackbarHost(hostState = snackBarHostState) { data ->
        Snackbar(
            action = {
                TextButton(onClick = { onDismiss() }) {
                    Text("Dismiss")
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            // Directly use data's snackbar message
            Text(text = data.visuals.message)
        }
    }

    if (!showSnackbar) {
        // Clear snackbar if no error to prevent unwanted display
        LaunchedEffect(Unit) {
            snackBarHostState.currentSnackbarData?.dismiss()
        }
    }
}
