package com.mwafaimk.unify.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun TextInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            if (value.isEmpty()) { // Show label only when the field is empty
                Text(label, color = Color.Blue)
            }
        },
        textStyle = TextStyle(color = Color.Blue), // Text input color
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.LightGray, // Background color when focused
            unfocusedContainerColor = Color.LightGray,    // Background color when not focused
            cursorColor = Color.Blue,                // Cursor color
            focusedTextColor = Color.Blue,           // Text color when focused
            unfocusedTextColor = Color.Blue          // Text color when not focused
        )
    )
}
