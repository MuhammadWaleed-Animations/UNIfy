package com.mwafaimk.unify.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.mwafaimk.unify.data.model.post.PostDetails
import com.mwafaimk.unify.ui.pages.addPost.AddPostScreen
import com.mwafaimk.unify.ui.pages.editPost.EditPostScreen
import com.mwafaimk.unify.ui.pages.editProfile.EditProfileScreen
import com.mwafaimk.unify.ui.pages.homePage.HomePage
import com.mwafaimk.unify.ui.pages.loadingWelcome.LoadingScreen
import com.mwafaimk.unify.ui.pages.loadingWelcome.WelcomeScreen
import com.mwafaimk.unify.ui.pages.signIn.SignInScreen
import com.mwafaimk.unify.ui.pages.signUp.SignUpScreen
import com.mwafaimk.unify.ui.pages.loadingWelcome.StartScreen
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

        composable(
            route = "${NavRoutes.EditPost}/{postDetails}",
            arguments = listOf(navArgument("postDetails") { type = NavType.StringType })
        ) { backStackEntry ->
            val postDetailsJson = backStackEntry.arguments?.getString("postDetails")
            if (!postDetailsJson.isNullOrEmpty()) {
                val postDetails = Gson().fromJson(postDetailsJson, PostDetails::class.java)
                EditPostScreen(onNavigate = navController::navigate, postContent = postDetails)
            }
        }

//        composable(
//            route = "${NavRoutes.EditPost}/{postDetails}",
//            arguments = listOf(
//                navArgument("postDetails") { type = NavType.StringType }
//            )
//        ) { backStackEntry ->
//            val postDetailsJson = backStackEntry.arguments?.getString("postDetails")
//            if (postDetailsJson != null) {
//                val postDetails = Gson().fromJson(postDetailsJson, PostDetails::class.java)
//                EditPostScreen(onNavigate = navController::navigate, postContent = postDetails)
//            }
//        }
        //composable(NavRoutes.UserProfile)   { UserPageScreen(onNavigate = navController::navigate)}
        composable(
            route = "userProfile/{userId}",
            arguments = listOf(
                navArgument("userId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: "unknown"
            UserPageScreen(onNavigate = navController::navigate, userId = userId)
        }

        composable(NavRoutes.EditProfile)   { EditProfileScreen(onNavigate = navController::navigate) }
    }
}
