package com.mwafaimk.unify.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mwafaimk.unify.ui.components.EditProfileButton
import com.mwafaimk.unify.ui.pages.addPost.AddPostScreen
import com.mwafaimk.unify.ui.pages.editPost.EditPostScreen
import com.mwafaimk.unify.ui.pages.editProfile.EditProfileScreen
import com.mwafaimk.unify.ui.pages.homePage.HomePage
import com.mwafaimk.unify.ui.pages.loadingWelcome.LoadingScreen
import com.mwafaimk.unify.ui.pages.loadingWelcome.WelcomeScreen
import com.mwafaimk.unify.ui.pages.signIn.SignInScreen
import com.mwafaimk.unify.ui.pages.signIn.viewModel.SignInViewModel
import com.mwafaimk.unify.ui.pages.signUp.SignUpScreen
import com.mwafaimk.unify.ui.pages.loadingWelcome.StartScreen
import com.mwafaimk.unify.ui.pages.signUp.viewModel.SignUpViewModel
import com.mwafaimk.unify.ui.pages.splashScreen.SplashScreen
import com.mwafaimk.unify.ui.pages.userProfile.UserPageScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoutes.SplashScreen) {

        composable(NavRoutes.Start)         { StartScreen(onNavigate = navController::navigate) }
        composable(NavRoutes.SplashScreen)  { SplashScreen(onNavigate = navController::navigate) }
        composable(NavRoutes.SignUp)        { SignUpScreen(onNavigate = navController::navigate) }
        composable(NavRoutes.SignIn)        { SignInScreen(onNavigate = navController::navigate) }
        composable(NavRoutes.Home)          { HomePage(onNavigate = navController::navigate) }
        composable(NavRoutes.Loading)       { LoadingScreen(onNavigate = navController::navigate) }
        composable(NavRoutes.Welcome)       { WelcomeScreen(onNavigate = navController::navigate) }
        composable(NavRoutes.AddPost)       { AddPostScreen(onNavigate = navController::navigate) }
        composable(NavRoutes.EditPost)      { EditPostScreen(onNavigate = navController::navigate) }
        composable(NavRoutes.UserProfile)   { UserPageScreen(onNavigate = navController::navigate)}
        composable(NavRoutes.EditProfile)   { EditProfileScreen(onNavigate = navController::navigate) }
    }
}
