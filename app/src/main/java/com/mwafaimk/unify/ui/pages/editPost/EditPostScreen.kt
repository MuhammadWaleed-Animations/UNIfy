package com.mwafaimk.unify.ui.pages.editPost

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mwafaimk.unify.core.navigation.NavRoutes
import com.mwafaimk.unify.data.model.post.PostDetails
import com.mwafaimk.unify.data.model.post.updatePost.UpdatePostRequest
import com.mwafaimk.unify.ui.components.CategoryDropdown
import com.mwafaimk.unify.ui.components.PageTitle
import com.mwafaimk.unify.ui.components.PfpSelectionDialog
import com.mwafaimk.unify.ui.components.PostButton
import com.mwafaimk.unify.ui.components.TextInputField
import com.mwafaimk.unify.ui.pages.editPost.viewModel.EditPostViewModel

@Composable
fun EditPostScreen(onNavigate: (String) -> Unit, postContent:PostDetails, viewModel:EditPostViewModel = hiltViewModel()) {
    // State variables for each TextInputField
    var title by remember { mutableStateOf(postContent.title ?: "error no title") }
    var description by remember { mutableStateOf(postContent.description?:"error no description") }
    var timeFrame by remember { mutableStateOf(postContent.time?:"error no time") }
    var location by remember { mutableStateOf(postContent.location?: "error no location") }
    var membersRequired by remember { mutableStateOf(postContent.memberCount?:"error no member count")}
    var contactNumber by remember { mutableStateOf(postContent.contactInfo?:"error no contact info") }

    val toastMessage by viewModel.toastMessage.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(toastMessage) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearToastMessage() // Clear the message after showing the toast
        }
        if(toastMessage == "Post updated successfully!")
        {
            onNavigate(NavRoutes.Home)
        }
    }



    // State for selected category
    var selectedCategory by remember { mutableStateOf(postContent.category[0]?: "error no category") }

    // Determine button color based on Title and Description fields
    val isFormValid = true//title.isNotBlank() && description.isNotBlank() && selectedCategory != postContent.category[0]
    val buttonColor = if (isFormValid) Color.Red else Color.Gray

    // Scroll state
    val scrollState = rememberScrollState()

    val categories = listOf(
        "General", "Sports", "Study", "Music", "Lost and Found", "Event Notification",
        "Chill", "Fries", "Movie/Anime"
    ) // Full list for regular users


    // Handle form submission
    fun handleUpdatePost() {
        if (isFormValid) {
            // Create UpdatePostRequest
            val updatePostRequest = UpdatePostRequest(
                title = title,
                description = description,
                contactInfo = contactNumber,
                time = timeFrame,
                memberCount = membersRequired,
                category = listOf(selectedCategory),
                location = location
            )

            // Call ViewModel's updatePost function
            viewModel.updatePost(postId = postContent._id ?: "error", request = updatePostRequest)
        } else {
            // Handle form validation failure
            Log.e("EditPostScreen", "Form is invalid")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // Make it scrollable
            .background(Color.Black)
            .padding(16.dp),
    ) {

        PageTitle("EDIT POST")

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Select Post Category*",
            color = Color.Cyan,
            modifier = Modifier.padding(top=8.dp,bottom = 0.dp))
        CategoryDropdown(
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it },
            categories = categories
        )
        Text(text = "Title*",
            color = Color.Cyan,
            modifier = Modifier.padding(top=8.dp,bottom = 0.dp))

        TextInputField(
            label = "Set Title *",
            value = title,
            onValueChange = { title = it }
        )
        Text(text = "Description*",
            color = Color.Cyan,
            modifier = Modifier.padding(top=8.dp,bottom = 0.dp))
        TextInputField(
            label = "Set Description *",
            value = description,
            onValueChange = { description = it }
        )
        Text(text = "Time Frame",
            color = Color.Cyan,
            modifier = Modifier.padding(top=8.dp,bottom = 0.dp))
        TextInputField(
            label = "Set Time Frame",
            value = timeFrame,
            onValueChange = { timeFrame = it }
        )
        Text(text = "Location",
            color = Color.Cyan,
            modifier = Modifier.padding(top=8.dp,bottom = 0.dp))
        TextInputField(
            label = "Set Location (won't show on post)",
            value = location,
            onValueChange = { location = it }
        )
        Text(text = "Member Count",
            color = Color.Cyan,
            modifier = Modifier.padding(top=8.dp,bottom = 0.dp))
        TextInputField(
            label = "Members Required",
            value = membersRequired,
            onValueChange = { membersRequired = it }
        )
        Text(text = "Contact No.",
            color = Color.Cyan,
            modifier = Modifier.padding(top=8.dp,bottom = 0.dp))
        TextInputField(
            label = "Contact Number",
            value = contactNumber,
            onValueChange = { contactNumber = it }
        )

        // Centering and dynamically changing button color
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            PostButton(
                onClick = { handleUpdatePost()

                          },
                buttonColor = buttonColor,
                buttonText = "UPDATE"
            )
        }
    }


}
