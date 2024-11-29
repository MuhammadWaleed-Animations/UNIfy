package com.mwafaimk.unify.ui.pages.userProfile.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwafaimk.unify.core.util.datastore.AuthStateManager
import com.mwafaimk.unify.core.util.datastore.DataManager
import com.mwafaimk.unify.data.model.user.login.LoginResponse
import com.mwafaimk.unify.data.repository.network.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val dataManager: DataManager,
    private val authStateManager: AuthStateManager
) : ViewModel() {

    private val _userState = MutableStateFlow<LoginResponse?>(null)
    val userState: StateFlow<LoginResponse?> = _userState

    init {
        // Observe user data from DataManager
        viewModelScope.launch {
            dataManager.userFlow.collectLatest { user ->
                _userState.value = user
            }
        }
    }
    fun signOut()
    {
        deleteUser()
        clearLoginState()
    }

    // Delete user data
    fun deleteUser() {
        viewModelScope.launch {
            dataManager.deleteUser()
        }
    }

    // Method to clear login state
    fun clearLoginState() {
        viewModelScope.launch {
            authStateManager.clearLoginState()
        }
    }
}