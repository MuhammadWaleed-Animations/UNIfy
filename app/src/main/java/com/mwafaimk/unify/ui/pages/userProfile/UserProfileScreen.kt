package com.mwafaimk.unify.ui.pages.userProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mwafaimk.unify.core.navigation.NavRoutes
import com.mwafaimk.unify.ui.components.EditProfileButton
import com.mwafaimk.unify.ui.components.UserProfileContent
import com.mwafaimk.unify.ui.components.signoutbutton

@Composable
fun UserPageScreen(onNavigate: (String) -> Unit) {
    // State to manage dialog visibility
    var showDialog by remember { mutableStateOf(false) }

    // Black background container
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Display the profile content at the top using our function in UserProfileContent
        UserProfileContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopCenter)
        )

        // Buttons at the bottom center
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Sign Out button called
            signoutbutton()

            // Edit Profile button called
            //EditProfileButton()
            Button(
                onClick = { onNavigate(NavRoutes.EditProfile) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(
                    text = "edit profile",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }


    }

}

