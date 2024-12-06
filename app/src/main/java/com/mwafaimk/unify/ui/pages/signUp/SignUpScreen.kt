package com.mwafaimk.unify.ui.pages.signUp

import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mwafaimk.unify.core.navigation.NavRoutes
import com.mwafaimk.unify.ui.components.Logo
import com.mwafaimk.unify.ui.components.PfpSelectionDialog
import com.mwafaimk.unify.ui.components.ProfilePlaceholder
import com.mwafaimk.unify.ui.pages.signUp.viewModel.SignUpViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(onNavigate: (String) -> Unit , viewModel: SignUpViewModel = hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var identity by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var selectedPfp by remember { mutableStateOf("") }
    var organization by remember { mutableStateOf("") }
    
    var i by remember { mutableStateOf(0) } //change to 0

    val uiState by viewModel.uiState.collectAsState()

    // Dynamic button enable state based on the current step
    val isButtonEnabled = when (i) {
        0 -> email.isNotBlank() && password.isNotBlank()
        1 -> identity.isNotBlank() // Step 1: Enable if identity is not blank
        2 -> phoneNumber.isNotBlank() // Step 2: Enable if phone number is not blank
        3 -> selectedPfp.isNotBlank() // Step 3: Enable by default (you can add custom validation here if needed)
        else -> false
    }
    val context = LocalContext.current
    LaunchedEffect(uiState.emailError) {
        if (uiState.emailError.isNotEmpty()) {
            Toast.makeText(context, uiState.emailError, Toast.LENGTH_SHORT).show()
            viewModel.emptyError()
        }
    }
    LaunchedEffect(uiState.usernameError) {
        if (uiState.usernameError.isNotEmpty()) {
            Toast.makeText(context, uiState.usernameError, Toast.LENGTH_SHORT).show()
            viewModel.emptyError()
        }
    }
    LaunchedEffect(uiState.signUpError) {
        if (uiState.signUpError.isNotEmpty()) {
            Toast.makeText(context, uiState.signUpError, Toast.LENGTH_SHORT).show()
            viewModel.emptyError()
        }
    }
    LaunchedEffect(uiState.signUpSuccess) {
        if (uiState.signUpSuccess) {
            viewModel.emptyError()
            onNavigate(NavRoutes.Loading) // Navigate after signup
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo()

        if(i == 0)
        {
            // Add space below the logo
            Spacer(modifier = Modifier.height(100.dp))

            // Text aligned to the left above the text field
            Text(
                text = "Make your UNI email known",
                color = Color.Cyan,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth() // Ensure full width
                    .align(Alignment.Start) // Align text to the left
            )

            // TextField for entering unique identity
            TextField(
                value = email,
                onValueChange = {
                    email = it
                    organization = email.substringAfter('@',"")
                    Log.d("Organiztion","" + organization)
                                },

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
                text = "Set the code word",
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
            Spacer(modifier = Modifier.height(30.dp))
            if (uiState.isLoading) {
                CircularProgressIndicator()
            }
        }
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
            Spacer(modifier = Modifier.height(30.dp))
            if (uiState.isLoading) {
                CircularProgressIndicator()
            }
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
            ProfilePlaceholder(pfp = selectedPfp)
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { showDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("Select Profile Picture", color = Color.Cyan)
            }
            Spacer(modifier = Modifier.height(16.dp))
            //Spacer(modifier = Modifier.height(30.dp))
            if (uiState.isLoading) {
                CircularProgressIndicator()
            }


        }
        if (showDialog) {
            PfpSelectionDialog(
                onDismissRequest = { showDialog = false },
                onImageSelected = { selectedPfp = it
                    Log.d("PFP seclected", "" + selectedPfp)
                }
            )
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
                    when (i) {
                        0 -> viewModel.checkEmail(email) // Check email at step 0
                        1 -> viewModel.checkUsername(identity) // Check username at step 1
                        2 -> i++
                        3 -> viewModel.signUp(email = email, username = identity, password = password, contactInfo = phoneNumber,pfp =  selectedPfp, organization = organization)
                    }

                    Log.d("ui state ",""+uiState)
                    if ((uiState.isEmailUnique && i== 0) || (uiState.isUsernameUnique && i== 1)) i++ // Increment step on success
                },
                enabled = isButtonEnabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isButtonEnabled) Color.Black else Color.Gray
                )
            ) {
                Text(
                    text = if(i<3) "Go to Next Mission" else "Enter Battle Field",
                    color = if (isButtonEnabled) Color.Green else Color.DarkGray
                )
            }
        }
    }


    LaunchedEffect(uiState) {
        when {
            i == 0 && uiState.isEmailUnique -> i++
            i == 1 && uiState.isUsernameUnique -> i++
        }
    }
}

