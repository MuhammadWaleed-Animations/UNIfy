package com.mwafaimk.unify.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun EventDetails(postData: PostData) {
    Text(
        text = postData.eventTitle,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = postData.eventDescription,
        fontSize = 13.sp,
        color = Color.Black
    )
}
