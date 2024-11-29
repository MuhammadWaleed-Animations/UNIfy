package com.mwafaimk.unify.ui.pages.editProfile.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwafaimk.unify.core.util.datastore.AuthStateManager
import com.mwafaimk.unify.core.util.datastore.DataManager
import com.mwafaimk.unify.data.model.user.User
import com.mwafaimk.unify.data.model.user.login.LoginRequest
import com.mwafaimk.unify.data.model.user.login.LoginResponse
import com.mwafaimk.unify.data.model.user.updateUser.UpdateUserRequest
import com.mwafaimk.unify.data.model.user.updateUser.UpdateUserResponse
import com.mwafaimk.unify.data.repository.network.NetworkRepository
import com.mwafaimk.unify.ui.pages.signIn.viewModel.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val dataManager: DataManager,
) : ViewModel() {

    private val _userState = MutableStateFlow<LoginResponse?>(null)
    val userState: StateFlow<LoginResponse?> = _userState

    private val _updateStatus = MutableStateFlow<Result<UpdateUserResponse>?>(null)
    val updateStatus: StateFlow<Result<UpdateUserResponse>?> = _updateStatus

    private val _uiState = MutableStateFlow(EditState())
    val uiState: StateFlow<EditState> get() = _uiState





    init {
        viewModelScope.launch {
            dataManager.userFlow.collectLatest { user ->
                if (user != null) {
                    Log.d("EditProfileViewModel", "User data received: $user")
                    _userState.value = user
                } else {
                    Log.d("EditProfileViewModel", "No user data available")
                }
            }
        }
    }

    // Save user data
    fun saveUser(user: LoginResponse) {
        viewModelScope.launch {
            dataManager.saveUser(user)
        }
    }

    fun updateUser(
        userId: String,
        username: String?,
        email: String?,
        contactInfo: String?,
        profilePicture: String?,
        organization: String?,
        admin: Boolean?
    ) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val request = UpdateUserRequest(
                    username = username,
                    email = email,
                    contactInfo = contactInfo,
                    profilePicture = profilePicture,
                    organization = organization,
                )
                val response = networkRepository.updateUser(userId, request)
                if (response.message == "User updated successfully") {
                    _uiState.value = _uiState.value.copy(EditError = null, EditSuccess = true)
                    saveUser(LoginResponse(response.user,admin))
                } else {
                    _uiState.value = _uiState.value.copy(EditError = response.message)
                }
                Log.d("SignIn","Response: "+response)
            } catch (e: Exception) {
                Log.e("EditProfileViewModel", "Error updating user", e)
                _uiState.value = _uiState.value.copy(EditError = "Edit Failed! Check Internet Connection")
            }
            finally{
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    fun clearState()
    {
        _uiState.value = _uiState.value.copy(EditError =  null)
    }
}

data class EditState(
    val EditSuccess: Boolean = false,
    val EditError: String? = null,
    val isLoading: Boolean = false
)
