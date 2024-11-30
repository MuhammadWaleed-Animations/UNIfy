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
import com.mwafaimk.unify.data.model.post.PostDetails


@Composable
fun EventDetails(postData: PostDetails) {
    Text(
        text = postData.title,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = postData.description,
        fontSize = 13.sp,
        color = Color.Black
    )
}
