package com.mwafaimk.unify.ui.pages.userProfile.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwafaimk.unify.core.util.datastore.AuthStateManager
import com.mwafaimk.unify.core.util.datastore.DataManager
import com.mwafaimk.unify.data.model.post.createPost.CreatePostRequest
import com.mwafaimk.unify.data.model.user.User
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
    private val authStateManager: AuthStateManager,
    private val repository: NetworkRepository
) : ViewModel() {

    private val _userState = MutableStateFlow<LoginResponse?>(null)
    val userState: StateFlow<LoginResponse?> = _userState

    private val _guserState = MutableStateFlow<User?>(null)
    val guserState: StateFlow<User?> = _guserState


    init {
        // Observe user data from DataManager
        viewModelScope.launch {
            dataManager.userFlow.collectLatest { user ->
                _userState.value = user
            }
        }
    }


    fun getUser(request:String ) {
        viewModelScope.launch {
            try {
                val response = repository.getUser(request)
                _guserState.value = response
                Log.d("profile","response: "+response)
            } catch (e: Exception) {
                Log.d("profile","response")
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