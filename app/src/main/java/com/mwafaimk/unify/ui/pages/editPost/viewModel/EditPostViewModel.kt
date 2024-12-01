package com.mwafaimk.unify.ui.pages.editPost.viewModel

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
import com.mwafaimk.unify.data.model.post.updatePost.UpdatePostRequest
import com.mwafaimk.unify.data.model.user.login.LoginResponse
import com.mwafaimk.unify.data.repository.network.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditPostViewModel  @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val dataManager: DataManager,
) : ViewModel() {

    private val _userState = MutableStateFlow<LoginResponse?>(null)
    val userState: StateFlow<LoginResponse?> = _userState

    private val _uiState = MutableStateFlow(EditState())
    val uiState: StateFlow<EditState> get() = _uiState


    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> get() = _toastMessage





    fun updatePost(postId: String, request:UpdatePostRequest) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val response = networkRepository.updatePost(postId,request)
                if (response.message == "Post updated successfully") {
                    _toastMessage.value = "Post updated successfully!"

                } else {
                    _toastMessage.value = response.message
                }
            } catch (e: Exception) {
                _toastMessage.value = "An error occurred. Please try again."
                Log.e("HomePageViewModel", "Error updating post", e)
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }




    fun clearState()
    {
        _uiState.value = _uiState.value.copy(EditError =  null)
    }
    fun clearToastMessage() {
        _toastMessage.value = null
    }

}

data class EditState(
    val EditSuccess: Boolean = false,
    val EditError: String? = null,
    val isLoading: Boolean = false,
)