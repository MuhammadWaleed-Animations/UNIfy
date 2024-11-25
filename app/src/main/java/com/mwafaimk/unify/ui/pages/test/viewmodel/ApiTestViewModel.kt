package com.mwafaimk.unify.ui.pages.test.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwafaimk.unify.data.model.admin.AdminDetails
import com.mwafaimk.unify.data.model.blockedUser.BlockUserRequest
import com.mwafaimk.unify.data.model.blockedUser.RemoveBlockedUserRequest
import com.mwafaimk.unify.data.model.post.createPost.CreatePostRequest
import com.mwafaimk.unify.data.model.post.done.ToggleDoneRequest
import com.mwafaimk.unify.data.model.post.updatePost.UpdatePostRequest
import com.mwafaimk.unify.data.model.user.createUser.CreateUserRequest
import com.mwafaimk.unify.data.model.user.login.LoginRequest
import com.mwafaimk.unify.data.model.user.updateUser.UpdateUserRequest
import com.mwafaimk.unify.data.repository.network.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiTestViewModel @Inject constructor(
    private val repository: NetworkRepository
) : ViewModel() {

    val responseLiveData = MutableLiveData<String>()

    fun testApi1() {
        viewModelScope.launch {
            try {
                //val response = repository.createUser(CreateUserRequest("mw1", "mw1@mw.com", "mw","mw","mw","mw"))
                val response = repository.togglePostDone("673ba58d85832516e770a43b",
                    ToggleDoneRequest(true)
                )
                responseLiveData.postValue("User Created: $response")
            } catch (e: Exception) {
                responseLiveData.postValue("Error: ${e.message}")
            }
        }
    }

    fun testApi2() {
        viewModelScope.launch {
            try {
                //6741ab377c20973a920bfe6b

                //val response = repository.getReportedPosts(AdminDetails("6741ab377c20973a920bfe6b"))
                //val response = repository.unReportPost("673ba58d85832516e770a43b",AdminDetails("6741ab377c20973a920bfe6b"))
                val response = repository.checkUsername("mw1")//deleteUserPosts("67421df185671b4418c6fc06")


                Log.d("API chal gyaaaaaaaa","Response:  "+ response )
                responseLiveData.postValue("User Logged In: $response")
            } catch (e: Exception) {
                responseLiveData.postValue("Error: ${e.message}")
                Log.d("API nhi chala","Error:  "+ e )
            }
        }
    }
}
