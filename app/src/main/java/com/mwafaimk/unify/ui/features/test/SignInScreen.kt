package com.mwafaimk.unify.ui.features.test

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mwafaimk.unify.ui.features.test.viewmodel.SignInViewModel
import kotlinx.coroutines.launch


@Composable
fun SignInScreen(viewModel: SignInViewModel) {
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Input fields for email and password (not implemented here)

        Button(onClick = {
            coroutineScope.launch {
                viewModel.signIn("johndoe@example.com", "securepassword")
            }
        }) {
            Text("Sign In")
        }
    }
}