package com.mwafaimk.unify.ui.pages.homePage.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwafaimk.unify.core.util.datastore.DataManager
import com.mwafaimk.unify.data.model.post.PostDetails
import com.mwafaimk.unify.data.model.post.UserIdDetails
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
                val userResponse: List<UpdatePostDetails>

                when {
                    category.equals("General", ignoreCase = true) -> {
                        Log.d("HomeViewModel", "Category matched: General")
                        response = networkRepository.getAllPosts(organization)
                        _responseLiveData.value = response
                        Log.d("General posts", "Post List: ${response[0]}, ${response[1]}")
                    }
                    category.equals("Own Posts", ignoreCase = true) -> {
                        Log.d("HomeViewModel", "Category matched: Own")

                        // Get the user data from userState
                        val currentUser = userState.value

                        if (currentUser != null) {
                            val userResponse = networkRepository.getUserPosts(userId)

                            // Map UpdatePostDetails to PostDetails
                            val mappedPosts = userResponse.map { updatePost ->
                                PostDetails(
                                    title = updatePost.title,
                                    description = updatePost.description,
                                    contactInfo = updatePost.contactInfo,
                                    time = updatePost.time,
                                    memberCount = updatePost.memberCount,
                                    category = updatePost.category,
                                    location = updatePost.location,
                                    timestamp = updatePost.timestamp,
                                    userId = UserIdDetails(
                                        _id = currentUser.user?._id?:"home own post current user _id error", // Use userId from userState
                                        username = currentUser.user?.username ?: "home own post current user username error", // Use username from userState
                                        email = currentUser.user?.email?:"home own post current user email error", // Use email from userState
                                        profilePicture = currentUser.user?.profilePicture // Use profile picture from userState
                                    ),
                                    reported = updatePost.reported,
                                    done = updatePost.done,
                                    _id = updatePost._id
                                )
                            }

                            _responseLiveData.value = mappedPosts
                            Log.d("Own posts", "Post List: ${mappedPosts.joinToString()}")
                        } else {
                            Log.e("HomeViewModel", "UserState is null, cannot map Own posts.")
                        }
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