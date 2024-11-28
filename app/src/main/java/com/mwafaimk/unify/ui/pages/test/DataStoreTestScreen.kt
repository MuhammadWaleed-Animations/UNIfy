package com.mwafaimk.unify.ui.pages.test


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mwafaimk.unify.data.model.user.User
import com.mwafaimk.unify.data.model.user.login.LoginResponse
import com.mwafaimk.unify.ui.pages.test.viewmodel.DataStoreTestViewModel

@Composable
fun DataStoreTestScreen(viewModel: DataStoreTestViewModel = hiltViewModel()) {
    val userState by viewModel.userState.collectAsState()

    var username by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }

    // UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "User Profile",
            style = MaterialTheme.typography.titleLarge
        )

        if (userState != null) {
            Text(text = "Username: ${userState!!.user?.username}")
            Text(text = "Email: ${userState!!.user?.email}")
        } else {
            Text(text = "No user data available.")
        }

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(8.dp)
                ) { innerTextField() }
            },
            singleLine = true
        )

        BasicTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(8.dp)
                ) { innerTextField() }
            },
            singleLine = true
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    val user = LoginResponse(
                        User("testing","testUsername","testEmail","testPassword","testContactInfo","testProfilePicture","testOrganization"),
                        isAdmin = true,
                    )
                    viewModel.saveUser(user)
                }
            ) {
                Text("Save User")
            }

            Button(
                onClick = {
                    viewModel.deleteUser()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Delete User")
            }
        }
    }
}
