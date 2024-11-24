package com.mwafaimk.unify.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mwafaimk.unify.R

@Composable
fun ProfilePlaceholder(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.photo_placeholder),
        contentDescription = "Profile Photo Placeholder",
        modifier = modifier
            .size(170.dp)
            .clip(CircleShape)
            .border(2.dp, Color.Gray, CircleShape),
        contentScale = ContentScale.Crop
    )
}
