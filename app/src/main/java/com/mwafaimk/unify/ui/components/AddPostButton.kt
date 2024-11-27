package com.mwafaimk.unify.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun AddPostButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(245.dp)
            .padding(vertical = 10.dp)
            .height(45.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Purple color
    ) {
        Text(text = "+ Add Post", color = Color.White)
    }
}