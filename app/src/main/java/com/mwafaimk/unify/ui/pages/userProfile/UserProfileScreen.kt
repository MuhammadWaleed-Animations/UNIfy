package com.mwafaimk.unify.ui.pages.userProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.mwafaimk.unify.R
import com.mwafaimk.unify.core.navigation.NavRoutes
import com.mwafaimk.unify.ui.components.EditProfileButton
import com.mwafaimk.unify.ui.components.UserProfileContent
import com.mwafaimk.unify.ui.components.signoutbutton
import com.mwafaimk.unify.ui.pages.userProfile.viewModel.UserProfileViewModel

@Composable
fun UserPageScreen(onNavigate: (String) -> Unit, viewModel:UserProfileViewModel = hiltViewModel()) {
    // State to manage dialog visibility
    val userState by viewModel.userState.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    val pfp = when (userState?.user?.profilePicture) {
        "Cat 1" -> R.drawable.cat1
        "Cat 2" -> R.drawable.cat2
        "Cat 3" -> R.drawable.cat3
        "Cat 4" -> R.drawable.cat4
        else -> R.drawable.a // Default value if not matched
    }
    // Black background container
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Display the profile content at the top using our function in UserProfileContent
        UserProfileContent(
            userState?.user?.username?:"unknown",
            userState?.user?.contactInfo?:"unknown",
            userState?.user?.email?:"unknown",
            pfp,

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopCenter),

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
            //signoutbutton()

            Button(
                onClick = { showDialog = true }, // Show confirmation dialog
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 8.dp)
            ) {
                Text(
                    text = "sign out",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            //FOR CONFIRMATION BROTHER
            if (showDialog) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x80000000)) // Semi-transparent black background
                        .clickable(enabled = false) {}, // Block interactions outside the dialog
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .background(Color.Gray, shape = CircleShape)
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Are you sure?",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // YES button
                            Button(
                                onClick = {
                                    viewModel.signOut()
                                    onNavigate(NavRoutes.Start)
                                          },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                                shape = CircleShape
                            ) {
                                Text(
                                    text = "YES",
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }

                            // NO button
                            Button(
                                onClick = { showDialog = false }, // Close dialog
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                                shape = CircleShape
                            ) {
                                Text(
                                    text = "NO",
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }

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
            Button(
                onClick = { onNavigate(NavRoutes.Home) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(
                    text = "Home Screen",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }


    }

}

