package com.mwafaimk.unify.ui.pages.homePage.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwafaimk.unify.core.util.datastore.DataManager
import com.mwafaimk.unify.data.model.admin.AdminDetails
import com.mwafaimk.unify.data.model.blockedUser.BlockUserRequest
import com.mwafaimk.unify.data.model.post.PostDetails
import com.mwafaimk.unify.data.model.post.UserIdDetails
import com.mwafaimk.unify.data.model.post.done.ToggleDoneRequest
import com.mwafaimk.unify.data.model.post.updatePost.UpdatePostDetails
import com.mwafaimk.unify.data.model.user.login.LoginRequest
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

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> get() = _toastMessage

    fun reportPost(postId: String, reason: String) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val response = networkRepository.reportPost(postId)
                if (response.message == "Post reported successfully") {
                    _toastMessage.value = "Post reported successfully!"
                } else {
                    _toastMessage.value = response.message
                }
            } catch (e: Exception) {
                _toastMessage.value = "An error occurred. Please try again."
                Log.e("HomePageViewModel", "Error reporting post", e)
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
    fun blockUser(email:String,reason:String,userId: String)
    {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val response = networkRepository.blockUser(BlockUserRequest(email,reason,userId))
                if (response.message == "User Blocked successfully") {
                    _toastMessage.value = "User Blocked successfully!"
                } else {
                    _toastMessage.value = response.message
                }
            } catch (e: Exception) {
                _toastMessage.value = "An error occurred. Please try again."
                Log.e("HomePageViewModel", "Error blocking user", e)
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }

    }
    fun deletePost(postId:String) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val response = networkRepository.deletePost(postId)
                if (response.message == "Post deleted successfully") {
                    _toastMessage.value = "Post deleted successfully!"
                } else {
                    _toastMessage.value = response.message
                }
            } catch (e: Exception) {
                _toastMessage.value = "An error occurred. Please try again."
                Log.e("HomePageViewModel", "Error deleting post", e)
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    fun togglePostDone(postId:String,done:Boolean) {
        viewModelScope.launch {
            try {
                //_uiState.value = _uiState.value.copy(isLoading = true)
                Log.d("togglePostDone", "Post ID: $postId, Done: $done")
                val response = networkRepository.togglePostDone(postId, ToggleDoneRequest(done))
                if (response.message == "Done toggled successfully") {
                    _toastMessage.value = "Done toggled successfully!"
                } else {
                    _toastMessage.value = response.message
                }
            } catch (e: Exception) {
                _toastMessage.value = "An error occurred. Please try again."
                Log.e("HomePageViewModel", "Error toggling done", e)
            } finally {
                //_uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    fun unReportPost(postId: String, adminId: String) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val response = networkRepository.unReportPost(postId, AdminDetails(adminId))
                if (response.message == "Post unreported successfully") {
                    _toastMessage.value = "Post uneReported successfully!"
                } else {
                    _toastMessage.value = response.message
                }
            } catch (e: Exception) {
                _toastMessage.value = "An error occurred. Please try again."
                Log.e("HomePageViewModel", "Error unReporting post", e)
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }


    fun homeResponse(
        userId:String,
        category: String,
        organization:String
    ) {
        val currentUser = userState.value
        //TODO set the server to return posts in descending order
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
                        Log.d("General posts", "Post List: ${response[0]}")
                    }
                    category.equals("Reported", ignoreCase = true) -> {
                        response = networkRepository.getReportedPosts(AdminDetails( currentUser?.user?._id?:"admin id not found in home reported posts"))
                        _responseLiveData.value = response
                    }
                    category.equals("Own Posts", ignoreCase = true) -> {
                        Log.d("HomeViewModel", "Category matched: Own")

                        // Get the user data from userState

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
                        response = networkRepository.getPostsByCategory(category, organization)
                        _responseLiveData.value = response
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
    fun clearToastMessage() {
        _toastMessage.value = null
    }

}

data class HomeState(
    val HomeSuccess: Boolean = false,
    val HomeError: String? = null,
    val isLoading: Boolean = false,
)