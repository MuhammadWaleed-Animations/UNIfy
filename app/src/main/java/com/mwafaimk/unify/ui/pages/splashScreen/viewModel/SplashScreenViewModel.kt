package com.mwafaimk.unify.ui.pages.splashScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwafaimk.unify.core.util.datastore.AuthStateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel@Inject constructor(
    private val authStateManager: AuthStateManager
) : ViewModel() {

    // Expose login state as a StateFlow
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    init {
        // Collect login state from DataStore
        viewModelScope.launch {
            authStateManager.isLoggedInFlow().collect { state ->
                _isLoggedIn.value = state
            }
        }
    }


}