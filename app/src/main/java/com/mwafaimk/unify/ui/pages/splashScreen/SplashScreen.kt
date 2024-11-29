package com.mwafaimk.unify.ui.pages.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mwafaimk.unify.R
import com.mwafaimk.unify.core.navigation.NavRoutes
import com.mwafaimk.unify.ui.pages.splashScreen.viewModel.SplashScreenViewModel
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(onNavigate: (String) -> Unit,viewModel: SplashScreenViewModel = hiltViewModel()) {
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    //val isLoggedIn = viewModel.isLoggedIn.collectAsState().value

    // Redirect if logged in
    LaunchedEffect(isLoggedIn) {
        delay(1700L)
        if (isLoggedIn) {
            onNavigate(NavRoutes.Home)
        }
        else
        {
            onNavigate(NavRoutes.Start)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // UNIfy Logo
        Image(
            painter = painterResource(id = R.drawable.unify_logo), // Ensure `unify_logo` exists in `res/drawable`
            contentDescription = "UNIfy Logo",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(200.dp))
        CircularProgressIndicator()

    }
}