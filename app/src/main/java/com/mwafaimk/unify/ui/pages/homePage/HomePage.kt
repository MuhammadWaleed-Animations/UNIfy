package com.mwafaimk.unify.ui.pages.homePage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mwafaimk.unify.R
import com.mwafaimk.unify.core.navigation.NavRoutes
import com.mwafaimk.unify.ui.components.PostCard
import com.mwafaimk.unify.ui.components.PostData


@Composable
fun HomePage(onNavigate: (String) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            // Profile Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Default Profile Icon
                Icon(
                    imageVector = Icons.Default.AccountCircle, // Default account circle icon
                    contentDescription = "Profile Icon",
                    modifier = Modifier
                        .size(48.dp)
                        .padding(end = 8.dp)
                )
                // Profile Details
                Column {
                    Text(
                        text = "Welcome, User!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "l226824@lhr.nu.edu.pk",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            // Posts List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 70.dp) // Space for Floating Button
            ) {
                items(10) { // Replace 10 with a dynamic value if needed
                    val postData = PostData(
                        profileImage = R.drawable.cat3,
                        userName = "Meow",
                        userEmail = "l226824@lhr.nu.edu.pk",
                        eventTitle = "Anime Watch Party",
                        eventDescription = "Watching Haikyu in CS13\nMaray lea bhi koi Snacks pakr lana",
                        eventTime = "3:30 - 4:50",
                        additionalInfo = "Jitnay zyada utna acha"
                    )
                    PostCard(postData = postData)
                }
            }
        }

        // Floating Action Button
        ExtendedFloatingActionButton(
            onClick ={onNavigate(NavRoutes.AddPost) },
            containerColor = Color(0xFF7C4DFF),
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Add, // Built-in add icon
                    contentDescription = "Add Post Icon",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Add Post",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}