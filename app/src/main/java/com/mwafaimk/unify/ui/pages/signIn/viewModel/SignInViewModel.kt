package com.mwafaimk.unify.ui.pages.signIn.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwafaimk.unify.core.util.datastore.AuthStateManager
import com.mwafaimk.unify.core.util.datastore.DataManager
import com.mwafaimk.unify.data.model.user.login.LoginRequest
import com.mwafaimk.unify.data.model.user.login.LoginResponse
import com.mwafaimk.unify.data.repository.network.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: NetworkRepository,
    private val dataManager: DataManager,
    private val authStateManager: AuthStateManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignInState())
    val uiState: StateFlow<SignInState> get() = _uiState


    // Method to set login state
    fun setLoginState(isLoggedIn: Boolean) {
        viewModelScope.launch {
            authStateManager.setLoginState(isLoggedIn)
        }
    }

    // Save user data
    fun saveUser(user: LoginResponse) {
        viewModelScope.launch {
            dataManager.saveUser(user)
        }
    }

    fun signIn(email:String,password:String) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val response = repository.loginUser(LoginRequest(email,password))
                if (response.message == null) {
                    _uiState.value = _uiState.value.copy(signInError = null, signInSuccess = true)
                    saveUser(response)
                    setLoginState(true)

                } else {
                    _uiState.value = _uiState.value.copy(signInError = response.message)
                }
                Log.d("SignIn","Response: "+response)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(signInError = "SignIn Failed! Check Internet Connection")
            }
            finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
                //Log.d("signup uiState",""+uiState)
            }
        }
    }
    fun emptyError()
    {
        _uiState.value = _uiState.value.copy(signInError =  null)
    }
}
data class SignInState(
    val signInSuccess: Boolean = false,
    val signInError: String? = null,
    val isLoading: Boolean = false
)


