package com.mwafaimk.unify.ui.pages.signIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mwafaimk.unify.core.navigation.NavRoutes
import com.mwafaimk.unify.ui.components.Logo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(onNavigate: (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isButtonEnabled = email.isNotBlank() && password.isNotBlank()// Enable button if input is not empty

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Central alignment for all components
    ) {
        // Centrally aligned logo
        Logo()

        // Add space below the logo
        Spacer(modifier = Modifier.height(100.dp))

        // Text aligned to the left above the text field
        Text(
            text = "Make your identity known",
            color = Color.Cyan,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth() // Ensure full width
                .align(Alignment.Start) // Align text to the left
        )

        // TextField for entering unique identity
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Enter Email", color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray, // TextField background color
                focusedLabelColor = Color.Cyan, // Label color when focused
                unfocusedLabelColor = Color.Gray // Label color when unfocused
            )
        )

        // Add space below the logo
        Spacer(modifier = Modifier.height(30.dp))

        // Text aligned to the left above the text field
        Text(
            text = "Tell the code word",
            color = Color.Cyan,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth() // Ensure full width
                .align(Alignment.Start) // Align text to the left
        )

        // TextField for entering unique identity
        TextField(
            value = password,
            onValueChange = {password = it },
            label = { Text("Enter Password", color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray, // TextField background color
                focusedLabelColor = Color.Cyan, // Label color when focused
                unfocusedLabelColor = Color.Gray // Label color when unfocused
            )
        )

        // Button aligned to the right below the text field
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.End // Align button to the right
        ) {
            Button(
                onClick = { if (isButtonEnabled) onNavigate(NavRoutes.Home) },
                enabled = isButtonEnabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isButtonEnabled) Color.Black else Color.Gray
                )
            ) {
                Text("Enter Battel Field", color = if (isButtonEnabled) Color.Green else Color.DarkGray)
            }
        }
    }
}