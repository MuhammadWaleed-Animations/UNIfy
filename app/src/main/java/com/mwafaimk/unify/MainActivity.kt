package com.mwafaimk.unify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

import androidx.lifecycle.viewmodel.compose.viewModel
import com.mwafaimk.unify.core.navigation.AppNavigation
import com.mwafaimk.unify.ui.pages.homePage.HomePage

import com.mwafaimk.unify.ui.pages.test.ApiTestScreen
import com.mwafaimk.unify.ui.pages.test.DataStoreTestScreen
import com.mwafaimk.unify.ui.pages.test.viewmodel.ApiTestViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // val apiTestViewModel = viewModel<ApiTestViewModel>()
            // ApiTestScreen(viewModel = apiTestViewModel)
            // HomePage(userEmail = "l226944@lhr.nu.edu.pk")
            // SignUpScreen()
            AppNavigation()
            //DataStoreTestScreen()
        }
    }
}