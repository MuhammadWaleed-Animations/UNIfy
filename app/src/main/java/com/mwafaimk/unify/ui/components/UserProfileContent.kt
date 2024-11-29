package com.mwafaimk.unify.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mwafaimk.unify.R


@Composable
fun UserProfileContent(
    username: String = "test",
    contactInfo: String = "test",
    email: String = "test",
    //status: String = "test",
    profileImageRes: Int, // Default image resource
    modifier: Modifier = Modifier
) {
    Spacer(modifier = Modifier.height(30.dp))

    Text(
        text = "► Profile",
        color = Color.White,
        fontSize = 20.sp,
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp)) // Top spacing

        // Profile Image
        Image(
            painter = painterResource(id = profileImageRes),
            contentDescription = "User Image",
            modifier = Modifier.size(200.dp).clip(CircleShape)
        )

        // User Name
        Text(
            text = "> $username",
            color = Color.Yellow,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // User Contact Info
        Text(
            text = "📞 $contactInfo",
            color = Color.Green,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // User Email
        Text(
            text = "\uD83D\uDCE7: $email",
            color = Color.Cyan,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

//        // User Status
//        Text(
//            text = "✎: $status",
//            color = Color.Magenta,
//            fontSize = 20.sp,
//            textAlign = TextAlign.Center
//        )
    }
}
