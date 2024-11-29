package com.mwafaimk.unify.ui.pages.editProfile

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mwafaimk.unify.R
import com.mwafaimk.unify.core.navigation.NavRoutes
import com.mwafaimk.unify.data.model.user.updateUser.UpdateUserResponse
import com.mwafaimk.unify.ui.components.PfpSelectionDialog
import com.mwafaimk.unify.ui.components.SaveChangesButton
import com.mwafaimk.unify.ui.pages.editProfile.viewModel.EditProfileViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@Composable
fun EditProfileScreen(onNavigate: (String) -> Unit, viewModel: EditProfileViewModel = hiltViewModel()) {
    // State to manage dialog visibility
    val userState by viewModel.userState.collectAsState()

    val _updateStatus = MutableStateFlow<Result<UpdateUserResponse>?>(null)
    val updateStatus: StateFlow<Result<UpdateUserResponse>?> = _updateStatus
    val uiState by viewModel.uiState.collectAsState()


    //// Global variables for username, contact number, bio, and profile picture
    var globalUsername by remember { mutableStateOf(userState?.user?.username ?:"Meow Error") }
    var globalContactNo by remember { mutableStateOf(userState?.user?.contactInfo ?:"Contact Admin") }
    //var globalBio = mutableStateOf("an eye for an eye wont make the whole world blind, just more pirate-y")
    var pfp by remember { mutableStateOf( userState?.user?.profilePicture ?:"Default") }

    LaunchedEffect(userState) {
        Log.d("EditProfileScreen", "UserState updated: $userState")
        // Update the global variables when userState changes
        globalUsername = userState?.user?.username ?: "Meow Error"
        globalContactNo = userState?.user?.contactInfo ?: "Contact Admin"
        pfp = userState?.user?.profilePicture ?:"Default"

    }

    val context = LocalContext.current

    LaunchedEffect(uiState) {
        Log.d("uiState",""+uiState)
        if (uiState.EditError != null) {
            Toast.makeText(context, uiState.EditError, Toast.LENGTH_SHORT).show()
            viewModel.clearState()
        }
        if (uiState.EditSuccess) {
            viewModel.clearState()
            onNavigate(NavRoutes.UserProfile)
        }
    }



    var showDialog by remember { mutableStateOf(false) }

//    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
//
//    // Launcher for the image picker
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        profileImageUri = uri // Update the profile image URI
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = ">profile/edit profile",
            color = Color.Yellow,
            fontSize = 15.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 8.dp)

        )

        Spacer(modifier = Modifier.height(32.dp))

        // Edit Username
        Text(
            text = "Edit username",
            color = Color.Magenta,
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 8.dp)
                .clip(CircleShape)
        )

        //Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = globalUsername,
            onValueChange = { globalUsername = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Edit Contact Number
        Text(
            text = "Edit contact no.",
            color = Color.Cyan,
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 8.dp)
        )
        BasicTextField(
            value = globalContactNo,
            onValueChange = { globalContactNo = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        //        // Profile Picture Section
//        if (profileImageUri != null) {
//            // Display the selected image
////            Image(
////                painter = rememberAsyncImagePainter(profileImageUri),
////                contentDescription = "Profile Picture",
////                modifier = Modifier
////                    .size(100.dp)
////                    .background(Color.Gray),
////                contentScale = ContentScale.Crop
////            )
//        } else {
//            // Placeholder for the image area
//            Box(
//                modifier = Modifier
//                    .size(100.dp)
//                    .background(Color.Gray),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(text = "No Image", color = Color.White)
//            }
//        }

        if (showDialog) {
            PfpSelectionDialog(
                onDismissRequest = { showDialog = false },
                onImageSelected = { pfp = it
                    Log.d("PFP seclected", "" + pfp)
                }
            )
        }

        // Profile Image
        Image(
            painter = painterResource(id = pfpHash(pfp)),
            contentDescription = "User Image",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )
      Spacer(modifier = Modifier.height(8.dp))


        // Button to upload/change profile picture
        Button(
            onClick = {  showDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "change profile picture", color = Color.White)
        }









//        // Profile Picture Section
//        if (profileImageUri != null) {
//            // Display the selected image
////            Image(
////                painter = rememberAsyncImagePainter(profileImageUri),
////                contentDescription = "Profile Picture",
////                modifier = Modifier
////                    .size(100.dp)
////                    .background(Color.Gray),
////                contentScale = ContentScale.Crop
////            )
//        } else {
//            // Placeholder for the image area
//            Box(
//                modifier = Modifier
//                    .size(100.dp)
//                    .background(Color.Gray),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(text = "No Image", color = Color.White)
//            }
//        }

//      Spacer(modifier = Modifier.height(8.dp))


//        // Button to upload/change profile picture
//        Button(
//            onClick = { launcher.launch("image/*") },
//            modifier = Modifier.align(Alignment.Start)
//        ) {
//            Text(text = "change/upload profile picture", color = Color.Red)
//        }

        Spacer(modifier = Modifier.height(16.dp))

//        // Edit Bio
//        Text(
//            text = "edit bio:",
//            color = Color.White,
//            fontSize = 18.sp,
//            modifier = Modifier
//                .align(Alignment.Start)
//                .padding(bottom = 8.dp)
//        )
//        BasicTextField(
//            value = globalBio.value,
//            onValueChange = {
//                if (it.length <= 100) globalBio.value = it
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.White)
//                .padding(8.dp),
//            singleLine = false
//        )

        Spacer(modifier = Modifier.height(32.dp))

        // Save Changes Button
        Column(
            modifier = Modifier
                .fillMaxSize() // Ensures the column takes the full screen
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom, // Places content at the bottom
            horizontalAlignment = Alignment.CenterHorizontally // Centers the button horizontally
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            }
            Button(
                onClick = { viewModel.updateUser(userState!!.user!!._id,globalUsername,userState!!.user!!.email, globalContactNo,pfp,userState!!.user!!.organization,userState!!.isAdmin) },
                modifier = Modifier.fillMaxWidth(0.5f) // Optional: Makes button width 50% of the parent
            ) {
                Text(text = "save changes", color = Color.White)
            }
        }

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