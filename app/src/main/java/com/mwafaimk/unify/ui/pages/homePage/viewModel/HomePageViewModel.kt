package com.mwafaimk.unify.ui.pages.homePage.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwafaimk.unify.core.util.datastore.DataManager
import com.mwafaimk.unify.data.model.post.PostDetails
import com.mwafaimk.unify.data.model.post.updatePost.UpdatePostDetails
import com.mwafaimk.unify.data.model.user.login.LoginResponse
import com.mwafaimk.unify.data.model.user.updateUser.UpdateUserRequest
import com.mwafaimk.unify.data.model.user.updateUser.UpdateUserResponse
import com.mwafaimk.unify.data.repository.network.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel  @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val dataManager: DataManager,
) : ViewModel() {

    private val _userState = MutableStateFlow<LoginResponse?>(null)
    val userState: StateFlow<LoginResponse?> = _userState

    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> get() = _uiState

    private val _responseLiveData = MutableStateFlow<List<PostDetails>?>(null)
    val responseLiveData: StateFlow<List<PostDetails>?> = _responseLiveData




    init {
        viewModelScope.launch {
            dataManager.userFlow.collectLatest { user ->
                if (user != null) {
                    Log.d("HomeScreenViewModel", "User data received: $user")
                    _userState.value = user
                } else {
                    Log.d("HomeScreenViewModel", "No user data available")
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

    fun homeResponse(
        userId:String,
        category: String,
        organization:String
    ) {
        viewModelScope.launch {
            try {
                Log.d("HomeViewModel", "inside Home Response with category: $category")
                _uiState.value = _uiState.value.copy(isLoading = true)
                val response: List<PostDetails>

                when {
                    category.equals("General", ignoreCase = true) -> {
                        Log.d("HomeViewModel", "Category matched: General")
                        response = networkRepository.getAllPosts(organization)
                        _responseLiveData.value = response
                        Log.d("General posts", "Post List: ${response[0]}, ${response[1]}")
                    }
                    category.equals("Own", ignoreCase = true) -> {
                        Log.d("HomeViewModel", "Category matched: Own")
                        // Fetch user's own posts
                        // response = networkRepository.getUserPosts(userId)
                    }
                    else -> {
                        Log.d("HomeViewModel", "Category matched: Other (else)")
                        response = networkRepository.getPostsByCategory("Environment", organization)
                        _responseLiveData.value = response
                        Log.d("Category posts", "Post List: ${response[0]}")
                    }
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error Fetching Data", e)
                _uiState.value = _uiState.value.copy(HomeError = "Edit Failed! Check Internet Connection")
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }

    }

    fun clearState()
    {
        _uiState.value = _uiState.value.copy(HomeError =  null)
    }
}

data class HomeState(
    val HomeSuccess: Boolean = false,
    val HomeError: String? = null,
    val isLoading: Boolean = false
)