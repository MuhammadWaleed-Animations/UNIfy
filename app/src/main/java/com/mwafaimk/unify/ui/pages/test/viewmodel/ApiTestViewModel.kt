package com.mwafaimk.unify.ui.pages.test.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwafaimk.unify.data.model.admin.AdminDetails
import com.mwafaimk.unify.data.model.blockedUser.BlockUserRequest
import com.mwafaimk.unify.data.model.blockedUser.RemoveBlockedUserRequest
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

    fun createUser() {
        viewModelScope.launch {
            try {
                //val response = repository.createUser(CreateUserRequest("mw1", "mw1@mw.com", "mw","mw","mw","mw"))
                val response = repository.createUser(CreateUserRequest("Kira", "Kira@shinigami.com", "ryuk","muhaha","cool",""))
                responseLiveData.postValue("User Created: $response")
            } catch (e: Exception) {
                responseLiveData.postValue("Error: ${e.message}")
            }
        }
    }

    fun loginUser() {
        viewModelScope.launch {
            try {
                //673b91e34f7b7f509561c898
                val response = repository.removeBlockedUser(RemoveBlockedUserRequest("naughty1@silly.com","6741ab377c20973a920bfe6b"))
                Log.d("API chal gyaaaaaaaa","removeBlockUser:  "+ response )
                responseLiveData.postValue("User Logged In: $response")
            } catch (e: Exception) {
                responseLiveData.postValue("Error: ${e.message}")
                Log.d("API nhi chala","removeBlockUser:  "+ e )
            }
        }
    }
}
