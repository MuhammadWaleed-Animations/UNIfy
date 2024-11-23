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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mwafaimk.unify.ui.components.Logo
import com.mwafaimk.unify.ui.components.ProfilePlaceholder

@Composable
fun ProfilePictureScreen(onFinish: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Centrally aligned logo
        Logo()

        // Add spacing below the logo
        Spacer(modifier = Modifier.height(50.dp))

        // Left-aligned text "Set Cute PFP"
        Text(
            text = "Set Cute PFP",
            color = Color.Magenta,
            modifier = Modifier
                .fillMaxWidth() // Make full width
                .align(Alignment.Start) // Align text to the left
        )

        // Add space below the text
        Spacer(modifier = Modifier.height(20.dp))

        // Centrally aligned profile placeholder
        ProfilePlaceholder()

        Spacer(modifier = Modifier.height(24.dp))

        // Button for selecting from the gallery (center-aligned)
        Button(
            onClick = { /* Open Gallery Logic */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text("Select from Gallery", color = Color.Cyan)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Right-aligned "End Mission" button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.End // Align to the right
        ) {
            Button(
                onClick = { onFinish() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("End Mission", color = Color.Green)
            }
        }

        Spacer(modifier = Modifier.height(70.dp))
    }
}