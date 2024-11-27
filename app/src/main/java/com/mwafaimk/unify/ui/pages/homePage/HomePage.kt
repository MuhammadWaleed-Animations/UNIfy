package com.mwafaimk.unify.ui.pages.homePage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mwafaimk.unify.R
import com.mwafaimk.unify.core.navigation.NavRoutes
import com.mwafaimk.unify.ui.components.CategoryDropdown
import com.mwafaimk.unify.ui.components.PostCard
import com.mwafaimk.unify.ui.components.PostData
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mwafaimk.unify.ui.components.AddPostButton
import com.mwafaimk.unify.ui.components.CategoryDropdown // Import the CategoryDropdown composable
import com.mwafaimk.unify.ui.pages.signUp.viewModel.SignUpViewModel


@Composable
fun HomePage(onNavigate: (String) -> Unit , userEmail: String,viewModel: SignUpViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Set the background color to black
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp) // Leave space for the floating button
        ) {
            // Profile Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile Details
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth() // Fill the available width
                    .padding(16.dp), // Add padding as needed
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start // Align children to the start
            ) {
                // Category Dropdown
                var selectedCategory by remember { mutableStateOf("Select...▼") }

                CategoryDropdown(
                    selectedCategory = selectedCategory,
                    onCategorySelected = { selectedCategory = it },
                    modifier = Modifier.weight(1f) // Allow the dropdown to take available space
                )

                // Profile Icon
                Icon(
                    painter = painterResource(id = R.drawable.cat2), // Replace with your image resource ID
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(48.dp)
                        .padding(start = 8.dp) // Padding between icon and dropdown
                        .clickable { onNavigate(NavRoutes.UserProfile) } // Add click action for profile icon
                )
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

       // Spacer(modifier = Modifier.height(20.dp))
        // Fixed AddPostButton at the bottom
        AddPostButton(
            onClick = {
                onNavigate(NavRoutes.AddPost)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter) // Position button at the bottom
                .padding(16.dp) // Optional: Padding to separate from the edges
        )
    }
}



