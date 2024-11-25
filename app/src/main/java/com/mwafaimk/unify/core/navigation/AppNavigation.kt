package com.mwafaimk.unify.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mwafaimk.unify.ui.pages.addPost.AddPostScreen
import com.mwafaimk.unify.ui.pages.editPost.EditPostScreen
import com.mwafaimk.unify.ui.pages.homePage.HomePage
import com.mwafaimk.unify.ui.pages.loadingWelcome.LoadingScreen
import com.mwafaimk.unify.ui.pages.loadingWelcome.WelcomeScreen
import com.mwafaimk.unify.ui.pages.signIn.SignInScreen
import com.mwafaimk.unify.ui.pages.signUp.SignUpScreen
import com.mwafaimk.unify.ui.pages.signUp.StartScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoutes.Start) {

        composable(NavRoutes.Start) { StartScreen(onNavigate = navController::navigate) }

        composable(NavRoutes.SignUp) { SignUpScreen(onNavigate = navController::navigate) }
        composable(NavRoutes.SignIn) { SignInScreen(onNavigate = navController::navigate) }
        //composable(NavRoutes.Home) { HomePage(onNavigate = navController::navigate) }
        composable(NavRoutes.Loading) { LoadingScreen(onNavigate = navController::navigate) }
        composable(NavRoutes.Welcome) { WelcomeScreen(onNavigate = navController::navigate) }
        composable(NavRoutes.AddPost) { AddPostScreen(onNavigate = navController::navigate) }
        composable(NavRoutes.EditPost) { EditPostScreen(onNavigate = navController::navigate) }
    }
}
