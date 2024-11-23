package com.mwafaimk.unify.ui.pages.test.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwafaimk.unify.data.model.user.createUser.CreateUserRequest
import com.mwafaimk.unify.data.model.user.login.LoginRequest
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
                val response = repository.createUser(CreateUserRequest("mw1", "mw1@mw.com", "mw","mw","mw","mw"))
                responseLiveData.postValue("User Created: $response")
            } catch (e: Exception) {
                responseLiveData.postValue("Error: ${e.message}")
            }
        }
    }

    fun loginUser() {
        viewModelScope.launch {
            try {
                val response = repository.loginUser(LoginRequest("mw1@mw.com", "mw"))
                responseLiveData.postValue("User Logged In: $response")
            } catch (e: Exception) {
                responseLiveData.postValue("Error: ${e.message}")
            }
        }
    }
}
