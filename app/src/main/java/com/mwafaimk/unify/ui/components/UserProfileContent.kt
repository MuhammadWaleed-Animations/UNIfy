package com.mwafaimk.unify.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mwafaimk.unify.R



@Composable
fun UserProfileContent(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(30.dp))

    Text(
        text = "►Profile",
        color = Color.White,
        fontSize = 20.sp,
      //  fontWeight = FontWeight.Bold
    )


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp)) // Top spacing


        Image(painterResource(id = R.drawable.a), contentDescription = "userImage", modifier = Modifier.size(200.dp))

        // User Name
        Text(
            text = "> Muiz ul Islam Khan",
            color = Color.Yellow,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // User Contact Info
        Text(
            text = "📞 420",
            color = Color.Green,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "\uD83D\uDCE7: l226944@lhr.nu.edu.pk",
            color = Color.Cyan,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))


        // User Status
        Text(
            text = "✎: an eye for an eye won't make the whole world blind, just more pirate-y",
            color = Color.Magenta,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}