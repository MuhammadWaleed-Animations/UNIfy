package com.mwafaimk.unify.ui.pages

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
import com.mwafaimk.unify.ui.components.Logo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberScreen(onNext: (String) -> Unit) {
    var phoneNumber by remember { mutableStateOf("") }
    val isButtonEnabled = phoneNumber.isNotEmpty() // Enable button when text field is not empty

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Align all elements centrally
    ) {
        // Centrally aligned logo
        Logo()

        // Add space below the logo
        Spacer(modifier = Modifier.height(100.dp))

        // Text aligned to the left above the text field
        Text(
            text = "Share Number if its Comfy",
            color = Color.Cyan,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth() // Ensure it spans the full width
                .align(Alignment.Start) // Align text to the left
        )

        // TextField for phone number input
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

        // Button aligned to the right below the text field
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.End // Align button to the right
        ) {
            Button(
                onClick = { if (isButtonEnabled) onNext(phoneNumber) },
                enabled = isButtonEnabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isButtonEnabled) Color.Black else Color.Gray
                )
            ) {
                Text("Go To Next Mission", color = if (isButtonEnabled) Color.Green else Color.DarkGray)
            }
        }
    }
}