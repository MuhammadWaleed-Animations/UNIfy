package com.mwafaimk.unify.ui.pages.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(25.dp), // Add padding to avoid text touching the edges
        verticalArrangement = Arrangement.Center // Center vertically
    ) {
        Text(
            text = "Welcome to",
            color = Color.White,
            fontSize = 45.sp, // Larger font size
            fontWeight = FontWeight.ExtraBold, // Bolder text
            modifier = Modifier.padding(bottom = 22.dp) // Space between texts
        )
        Text(
            text = "the Exciting",
            color = Color.White, // Brighter color for emphasis
            fontSize = 45.sp, // Larger font size
            fontWeight = FontWeight.ExtraBold, // Extra bold for more prominence
            modifier = Modifier.padding(bottom = 22.dp)
        )
        Text(
            text = "World of UNIfy",
            color = Color.White,
            fontSize = 43.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(top = 22.dp) // Space above
        )
    }
}