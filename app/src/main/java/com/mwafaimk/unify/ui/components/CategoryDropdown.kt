package com.mwafaimk.unify.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CategoryDropdown(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit // Callback to update parent state
) {
    var expanded by remember { mutableStateOf(false) }
    val categories = listOf("Sports", "Education", "General", "Lost and Found", "Event Notification")

    Box(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        ElevatedButton(
            onClick = { expanded = true },
            colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.Yellow),
            shape = RoundedCornerShape(4.dp), // Optional: Rectangular button
            modifier = Modifier.fillMaxWidth() // Ensure button fills the maximum width
        ) {
            Text(text = selectedCategory, color = Color.Black)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth() // Make dropdown menu fill the maximum width
                .background(Color.Yellow)
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        onCategorySelected(category) // Update selected category in parent
                        expanded = false
                    },
                    text = { Text(text = category) }
                )
            }
        }
    }
}