package com.mwafaimk.unify.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mwafaimk.unify.ui.components.Logo

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Centrally aligned logo
        Logo()

        // Add space below the logo
        Spacer(modifier = Modifier.height(16.dp))

        // Circular progress indicator
        CircularProgressIndicator(color = Color.Cyan)

        // Add space below the progress indicator
        Spacer(modifier = Modifier.height(8.dp))

        // Enlarged text size for "Greatness in Progress"
        Text(
            text = "Greatness in Progress",
            color = Color.White,
            fontSize = 30.sp, // Enlarged font size
            fontWeight = FontWeight.Bold // Optional: Make the text bold for emphasis
        )
    }
}