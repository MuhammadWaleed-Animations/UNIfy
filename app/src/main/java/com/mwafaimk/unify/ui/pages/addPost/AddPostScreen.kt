package com.mwafaimk.unify.ui.pages.addPost

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mwafaimk.unify.core.navigation.NavRoutes
import com.mwafaimk.unify.data.model.post.createPost.CreatePostRequest
import com.mwafaimk.unify.ui.components.CategoryDropdown
import com.mwafaimk.unify.ui.components.PageTitle
import com.mwafaimk.unify.ui.components.PostButton
import com.mwafaimk.unify.ui.components.TextInputField
import com.mwafaimk.unify.ui.pages.addPost.viewModel.AddPostViewModel

@Composable
fun AddPostScreen(onNavigate: (String) -> Unit, viewModel: AddPostViewModel = hiltViewModel()) {

    // State variables for each TextInputField
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var timeFrame by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var membersRequired by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }


    // State for selected category
    var selectedCategory by remember { mutableStateOf("Select...") }

    // Determine button color based on Title and Description fields
    val isFormValid = title.isNotBlank() &&timeFrame.isNotBlank() && contactNumber.isNotBlank() &&location.isNotBlank() && membersRequired.isNotBlank() && description.isNotBlank() && selectedCategory != "Select..."
    val buttonColor = if (isFormValid) Color.Red else Color.Gray

    // Scroll state
    val scrollState = rememberScrollState()

    //ui state
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // Make it scrollable
            .background(Color.Black)
            .padding(16.dp),
    ) {

        PageTitle()

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Select Post Category*",
            color = Color.Cyan,
            modifier = Modifier.padding(top=8.dp,bottom = 0.dp))
        CategoryDropdown(
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
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
                onClick = {if (isFormValid) {
                    val postRequest = CreatePostRequest(
                        title = title,
                        description = description,
                        contactInfo = contactNumber.ifBlank { null },
                        time = timeFrame.ifBlank { null },
                        memberCount = membersRequired.ifBlank { null },
                        category = listOf(selectedCategory),
                        location = location.ifBlank { "N/A" },
                        userId = viewModel.userState.value?.user?._id ?: ""
                    )
                    viewModel.createPost(postRequest)
                } },
                buttonColor = buttonColor,
            )
        }
    }
    // Handle navigation or error display based on the UI state
    if (uiState.addPostSuccess) {
        viewModel.emptyError()
        onNavigate(NavRoutes.Home) // Navigate to a success screen
    } else if (uiState.addPostError != null) {
        // Show an error dialog or message
        Text(
            text = uiState.addPostError ?: "An error occurred",
            color = Color.Red,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}