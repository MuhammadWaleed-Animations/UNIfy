package com.mwafaimk.unify.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun PostButton(onClick: () -> Unit, buttonColor: Color, buttonText : String = "UPLOAD") {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)

    ) {
        Text(text = buttonText, color = Color.White)
    }
}
