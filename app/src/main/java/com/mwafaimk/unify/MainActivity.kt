package com.mwafaimk.unify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import com.mwafaimk.unify.ui.features.test.SignInScreen

import androidx.lifecycle.viewmodel.compose.viewModel
import com.mwafaimk.unify.ui.features.test.viewmodel.SignInViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val signInViewModel = viewModel<SignInViewModel>()
            SignInScreen(viewModel = signInViewModel)
        }
    }
}