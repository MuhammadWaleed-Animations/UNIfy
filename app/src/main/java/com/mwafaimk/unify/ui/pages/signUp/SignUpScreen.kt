package com.mwafaimk.unify.ui.pages.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mwafaimk.unify.core.navigation.NavRoutes
import com.mwafaimk.unify.ui.components.Logo
import com.mwafaimk.unify.ui.components.ProfilePlaceholder


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(onNavigate: (String) -> Unit) {
    var identity by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var i by remember { mutableStateOf(1) }

    // Dynamic button enable state based on the current step
    val isButtonEnabled = when (i) {
        1 -> identity.isNotBlank() // Step 1: Enable if identity is not blank
        2 -> phoneNumber.isNotBlank() // Step 2: Enable if phone number is not blank
        3 -> true // Step 3: Enable by default (you can add custom validation here if needed)
        else -> false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo()

        if (i == 1) {
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                text = "Choose your UNIque Identity",
                color = Color.Cyan,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
            TextField(
                value = identity,
                onValueChange = { identity = it },
                label = { Text("Enter Identity", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.LightGray,
                    focusedLabelColor = Color.Cyan,
                    unfocusedLabelColor = Color.Gray
                )
            )
        } else if (i == 2) {
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                text = "Share Number if it's Comfy",
                color = Color.Cyan,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
            TextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.LightGray,
                    focusedLabelColor = Color.Cyan,
                    unfocusedLabelColor = Color.Cyan
                )
            )
        } else if (i == 3) {
            Text(
                text = "Set Cute PFP",
                color = Color.Magenta,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(20.dp))
            ProfilePlaceholder()
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { /* Open Gallery Logic */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("Select from Gallery", color = Color.Cyan)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Navigation button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    if (i < 3) i++ else if(i ==3)  onNavigate(NavRoutes.Loading) // Example navigation logic
                },
                enabled = isButtonEnabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isButtonEnabled) Color.Black else Color.Gray
                )
            ) {
                Text(
                    "Go to Next Mission",
                    color = if (isButtonEnabled) Color.Green else Color.DarkGray
                )
            }
        }
    }
}



//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SignUpScreen(onNavigate: (String) -> Unit) {
//    var identity by remember { mutableStateOf("") }
//    val isButtonEnabled = identity.isNotBlank() // Enable button if input is not empty
//    var phoneNumber by remember { mutableStateOf("") }
//    //val isButtonEnabled = phoneNumber.isNotEmpty() // Enable button when text field is not empty
//
//    var i by remember { mutableStateOf(1)}
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Black)
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally // Central alignment for all components
//    ) {
//        // Centrally aligned logo
//        Logo()
//
//
//
//        if( i == 1) {
//            // Add space below the logo
//            Spacer(modifier = Modifier.height(100.dp))
//            // Text aligned to the left above the text field
//
//            Text(
//                text = "Choose your UNIque Identity",
//                color = Color.Cyan,
//                modifier = Modifier
//                    .padding(bottom = 8.dp)
//                    .fillMaxWidth() // Ensure full width
//                    .align(Alignment.Start) // Align text to the left
//            )
//
//            // TextField for entering unique identity
//            TextField(
//                value = identity,
//                onValueChange = { identity = it },
//                label = { Text("Enter Identity", color = Color.Gray) },
//                modifier = Modifier.fillMaxWidth(),
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = Color.LightGray, // TextField background color
//                    focusedLabelColor = Color.Cyan, // Label color when focused
//                    unfocusedLabelColor = Color.Gray // Label color when unfocused
//                )
//            )
//        }
//        else if (i == 2)
//        {
//            // Add space below the logo
//            Spacer(modifier = Modifier.height(100.dp))
//            // Text aligned to the left above the text field
//            Text(
//                text = "Share Number if its Comfy",
//                color = Color.Cyan,
//                modifier = Modifier
//                    .padding(bottom = 8.dp)
//                    .fillMaxWidth() // Ensure it spans the full width
//                    .align(Alignment.Start) // Align text to the left
//            )
//
//            // TextField for phone number input
//            TextField(
//                value = phoneNumber,
//                onValueChange = { phoneNumber = it },
//                label = { Text("Phone Number", color = Color.Gray) },
//                modifier = Modifier.fillMaxWidth(),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = Color.LightGray,
//                    focusedLabelColor = Color.Cyan,
//                    unfocusedLabelColor = Color.Cyan
//                )
//            )
//        }
//        else if(i == 3)
//        {
//            // Left-aligned text "Set Cute PFP"
//            Text(
//                text = "Set Cute PFP",
//                color = Color.Magenta,
//                modifier = Modifier
//                    .fillMaxWidth() // Make full width
//                    .align(Alignment.Start) // Align text to the left
//            )
//
//            // Add space below the text
//            Spacer(modifier = Modifier.height(20.dp))
//
//            // Centrally aligned profile placeholder
//            ProfilePlaceholder()
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            // Button for selecting from the gallery (center-aligned)
//            Button(
//                onClick = { /* Open Gallery Logic */ },
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
//            ) {
//                Text("Select from Gallery", color = Color.Cyan)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//        }
//
//        // Button aligned to the right below the text field
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 8.dp),
//            horizontalArrangement = Arrangement.End // Align button to the right
//        ) {
//            Button(
//                onClick = {
//
//                    i = i+1
//
//                },
//                enabled = isButtonEnabled,
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = if (isButtonEnabled) Color.Black else Color.Gray
//                )
//            ) {
//                Text("Go to Next Mission", color = if (isButtonEnabled) Color.Green else Color.DarkGray)
//            }
//        }
//    }
//}