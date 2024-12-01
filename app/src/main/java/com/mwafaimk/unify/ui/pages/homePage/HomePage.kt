package com.mwafaimk.unify.ui.pages.homePage

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mwafaimk.unify.R
import com.mwafaimk.unify.core.navigation.NavRoutes
import com.mwafaimk.unify.ui.components.CategoryDropdown
import com.mwafaimk.unify.ui.components.PostCard
import com.mwafaimk.unify.ui.components.PostData
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.gson.Gson
import com.mwafaimk.unify.data.model.post.PostDetails
import com.mwafaimk.unify.data.model.post.UserIdDetails
import com.mwafaimk.unify.ui.components.AddPostButton
import com.mwafaimk.unify.ui.pages.homePage.viewModel.HomePageViewModel



@Composable
fun HomePage(onNavigate: (String) -> Unit , viewModel: HomePageViewModel = hiltViewModel()) {

    val userState by viewModel.userState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val posts by viewModel.responseLiveData.collectAsState() // Collect the list of posts
    val toastMessage by viewModel.toastMessage.collectAsState()

    var selectedCategory by remember { mutableStateOf("General") }


    // Filter categories based on isAdmin
    val categories = if (userState?.isAdmin == true) {
        listOf("General","Reported","Own Posts") // Only "Reported" for admin
    } else {
        listOf(
            "General", "Sports", "Study", "Music", "Lost and Found", "Event Notification",
            "Chill", "Fries", "Movie/Anime", "Own Posts"
        ) // Full list for regular users
    }


    // Show Toast when toastMessage changes
    val context = LocalContext.current
    LaunchedEffect(toastMessage) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearToastMessage() // Clear the message after showing the toast
        }
    }
    // Call the API when "General" is selected
    LaunchedEffect(selectedCategory) {
        if(selectedCategory == "General")
        {
            kotlinx.coroutines.delay(700L)
        }
        Log.d("HomeScreen", "LaunchedEffect triggered for selectedCategory: $selectedCategory")
        if(userState?.user != null)
        {
            Log.d("userId","Mil gai ha")

            viewModel.homeResponse(userId = userState?.user?._id?:"Error", category = selectedCategory, organization = userState?.user?.organization?:"Error")
        }
        else{
            Log.d("Whattttt","Where did this even go")
        }

    }

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

                CategoryDropdown(
                    selectedCategory = selectedCategory,
                    onCategorySelected = { selectedCategory = it },
                    categories = categories, // Pass filtered categories
                    modifier = Modifier.weight(1f) // Allow the dropdown to take available space
                )

//                // Profile Icon
//                Icon(
//                    painter = painterResource(id = R.drawable.cat2), // Replace with your image resource ID
//                    contentDescription = "Profile Image",
//                    modifier = Modifier
//                        .size(48.dp)
//                        .padding(start = 8.dp) // Padding between icon and dropdown
//                        .clickable { onNavigate(NavRoutes.UserProfile) } // Add click action for profile icon
//                )
                Image(
                    painter = painterResource(id = pfpHash(userState?.user?.profilePicture?:"Default")), // Load the drawable resource
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .clickable { onNavigate(NavRoutes.UserProfile) }
                )


            }


            if (uiState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(), // Fill the entire screen
                    contentAlignment = Alignment.Center // Center content horizontally and vertically
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(48.dp), // Optional: Adjust the size of the loader
                        color = Color.Yellow // Optional: Set the color to match your theme
                    )
                }
            }


            // Posts List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 70.dp) // Space for Floating Button
            ) {
                // 🚫 ✅
                if (!posts.isNullOrEmpty()) {
                    items(posts!!) { post -> // Iterate over the list of PostDetails

                        // Define actions based on the selected category
                        val postActions = if (selectedCategory == "Reported") {
                            PostActions(
                                icon1 = "🚫",
                                icon2 = "✅",
                                onReport = { reason ->
                                    viewModel.blockUser(post?.userId?.email?:"email not found while blocking", reason,userState?.user?._id?:"Admin not found while blocking") // Clear report logic
                                    if (userState?.user != null) {
                                        viewModel.homeResponse(
                                            userId = userState?.user?._id ?: "Error",
                                            category = selectedCategory,
                                            organization = userState?.user?.organization ?: "Error"
                                        )
                                    }
                                },
                                onClear = {
                                    viewModel.unReportPost(post._id, userState?.user?._id ?: " ") // Clear report logic
                                    if (userState?.user != null) {

                                        viewModel.homeResponse(
                                            userId = userState?.user?._id ?: "Error",
                                            category = selectedCategory,
                                            organization = userState?.user?.organization ?: "Error"
                                        )
                                    }
                                }
                            )
                        }
                        else if(selectedCategory == "Own Posts") {
                            // ♡ 💙 🗑️ 📝
                            PostActions(
                                icon1 ="🗑️",
                                icon2 = "📝",
                                onReport = { reason ->
                                    viewModel.deletePost(post?._id?:"Error Post without id in delete post") // Clear report logic
                                    if (userState?.user != null) {
                                        viewModel.homeResponse(
                                            userId = userState?.user?._id ?: "Error",
                                            category = selectedCategory,
                                            organization = userState?.user?.organization ?: "Error"
                                        )
                                    }
                                },
                                onClear = {
                                    val postDetailsJson = Gson().toJson(post)
                                    onNavigate("${NavRoutes.EditPost}/$postDetailsJson")
//                                    if (userState?.user != null) {
//
//                                        viewModel.homeResponse(
//                                            userId = userState?.user?._id ?: "Error",
//                                            category = selectedCategory,
//                                            organization = userState?.user?.organization ?: "Error"
//                                        )
//                                    }
                                }
                            )
                        }
                        else {
                            PostActions(
                                icon1 = "🚩",
                                icon2 = "\uD83D\uDD17",
                                onReport = { reason ->
                                    viewModel.reportPost(post._id, reason) // Standard report logic
                                    if (userState?.user != null) {
                                        viewModel.homeResponse(
                                            userId = userState?.user?._id ?: "Error",
                                            category = selectedCategory,
                                            organization = userState?.user?.organization ?: "Error"
                                        )
                                    }
                                },
                                onClear = {
                                }
                            )
                        }

                        PostCard(
                            postData = PostDetails(
                                title = post.title,
                                description = post.description,
                                contactInfo = post.contactInfo,
                                time = post.time,
                                memberCount = post.memberCount,
                                category = post.category,
                                location = post.location,
                                timestamp = post.timestamp,
                                userId = UserIdDetails(
                                    post._id,
                                    post.userId.username,
                                    post.userId.email,
                                    post.userId.profilePicture
                                ),
                                reported = post.reported,
                                done = post.done,
                                _id = post._id
                            ),
                            icon1 = postActions.icon1,
                            icon2 = postActions.icon2,
                            onReport = postActions.onReport,
                            onClear = postActions.onClear
                        )
                    }
                } else {
                    item {
                        Text(
                            text = "No posts available",
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }

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
data class PostActions(
    val icon1: String,
    val icon2: String,
    val onReport: (String) -> Unit,
    val onClear: () -> Unit
)




