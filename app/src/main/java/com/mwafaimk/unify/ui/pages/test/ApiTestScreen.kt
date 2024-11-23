package com.mwafaimk.unify.ui.pages.test

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mwafaimk.unify.ui.pages.test.viewmodel.ApiTestViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApiTestScreen(viewModel: ApiTestViewModel) {
    val response = viewModel.responseLiveData.observeAsState("")

    Scaffold(
        topBar = { TopAppBar(title = { Text("API Test Screen") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(onClick = { viewModel.createUser() }) { Text("Create User") }
            Button(onClick = { viewModel.loginUser() }) { Text("Login User") }

            Divider()

            Text("Response:")
            Text(response.value ?: "No Response")
        }
    }
}
