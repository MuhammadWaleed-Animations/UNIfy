package com.mwafaimk.unify.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SaveChangesButton() {
    Column(
        modifier = Modifier
            .fillMaxSize() // Ensures the column takes the full screen
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom, // Places content at the bottom
        horizontalAlignment = Alignment.CenterHorizontally // Centers the button horizontally
    ) {
        Button(
            onClick = { /* Save changes logic here */ },
            modifier = Modifier.fillMaxWidth(0.5f) // Optional: Makes button width 50% of the parent
        ) {
            Text(text = "save changes", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSaveChangesButton() {
    SaveChangesButton()
}
