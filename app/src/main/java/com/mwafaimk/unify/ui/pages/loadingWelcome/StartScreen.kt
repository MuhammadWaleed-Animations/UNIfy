package com.mwafaimk.unify.ui.pages.loadingWelcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mwafaimk.unify.R
import com.mwafaimk.unify.core.navigation.NavRoutes

@Composable
fun StartScreen(onNavigate: (String) -> Unit) {
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

        Spacer(modifier = Modifier.height(100.dp))

        // Sign In Button
        Button(
            onClick = {  onNavigate(NavRoutes.SignIn) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .width(250.dp) // Reduced width
                .padding(vertical = 8.dp) // Added vertical spacing
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google), // Ensure `ic_google` exists in `res/drawable`
                    contentDescription = "Google Icon",
                    tint = Color.Unspecified, // Maintain original icon colors
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Log in with Uni Email",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }

        // Spacer for spacing between buttons
        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up Button (Identical to the Sign In button)
        Button(
            onClick = {  onNavigate(NavRoutes.SignUp) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .width(250.dp) // Reduced width
                .padding(vertical = 8.dp) // Added vertical spacing
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google), // Use the same Google icon for consistency
                    contentDescription = "Google Icon",
                    tint = Color.Unspecified, // Maintain original icon colors
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Sign up with Uni Email",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }
    }
}