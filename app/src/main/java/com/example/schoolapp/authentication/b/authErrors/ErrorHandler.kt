package com.example.schoolapp.authentication.b.authErrors

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorHandler(
    errorState: ErrorState?,
    onDismiss: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    var showSnackbar by remember { mutableStateOf(false) }

    LaunchedEffect(errorState) {
        val message = when (errorState) {
            is ErrorState.NetworkError -> errorState.message
            is ErrorState.AuthenticationError -> errorState.message
            is ErrorState.ValidationError -> errorState.message
            is ErrorState.EmptyFieldError -> errorState.message
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
            Text(text = data.visuals.message)
        }
    }

    if (!showSnackbar) {
        LaunchedEffect(Unit) {
            snackBarHostState.currentSnackbarData?.dismiss()
        }
    }
}