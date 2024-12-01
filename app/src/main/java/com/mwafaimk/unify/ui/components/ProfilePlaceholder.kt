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
fun ProfilePlaceholder(modifier: Modifier = Modifier, pfp:String = "Default") {
    Image(
        painter = painterResource(id = pfpHash(pfp)),
        contentDescription = "Profile Photo Placeholder",
        modifier = modifier
            .size(170.dp)
            .clip(CircleShape)
            .border(2.dp, Color.Gray, CircleShape),
        contentScale = ContentScale.Crop
    )
}

fun pfpHash(pfp: String):Int
{
    when (pfp) {
        "Cat 1" -> return R.drawable.cat1
        "Cat 2" -> return R.drawable.cat2
        "Cat 3" -> return R.drawable.cat3
        "Cat 4" -> return R.drawable.cat4
        else -> return R.drawable.a // Default value if not matched
    }
}
