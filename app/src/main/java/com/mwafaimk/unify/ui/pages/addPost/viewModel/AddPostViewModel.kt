package com.mwafaimk.unify.ui.pages.addPost.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwafaimk.unify.core.util.datastore.AuthStateManager
import com.mwafaimk.unify.core.util.datastore.DataManager
import com.mwafaimk.unify.data.model.post.createPost.CreatePostRequest
import com.mwafaimk.unify.data.model.user.login.LoginRequest
import com.mwafaimk.unify.data.model.user.login.LoginResponse
import com.mwafaimk.unify.data.repository.network.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddPostViewModel @Inject constructor(
    private val repository: NetworkRepository,
    private val dataManager: DataManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddPostState())
    val uiState: StateFlow<AddPostState> get() = _uiState

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

    fun createPost( postRequest: CreatePostRequest) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val response = repository.createPost(postRequest)
                if (response.message == "Post created successfully") {
                    _uiState.value = _uiState.value.copy(addPostError = null, addPostSuccess = true)

                } else {
                    _uiState.value = _uiState.value.copy(addPostError = response.message)
                }
                Log.d("SignIn","Response: "+response)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(addPostError = "SignIn Failed! Check Internet Connection")
            }
            finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
                //Log.d("signup uiState",""+uiState)
            }
        }
    }
    fun emptyError()
    {
        _uiState.value = _uiState.value.copy(addPostError =  null)
    }
}
data class AddPostState(
    val addPostSuccess: Boolean = false,
    val addPostError: String? = null,
    val isLoading: Boolean = false
)


