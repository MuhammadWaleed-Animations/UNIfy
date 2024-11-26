package com.mwafaimk.unify.ui.pages.signUp.viewModel

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Organization
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwafaimk.unify.data.repository.network.NetworkRepository
import com.mwafaimk.unify.data.model.user.checkUsername.CheckUsernameResponse
import com.mwafaimk.unify.data.model.user.createUser.CreateUserRequest
import com.mwafaimk.unify.data.model.user.createUser.CreateUserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: NetworkRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpState())
    val uiState: StateFlow<SignUpState> get() = _uiState


    fun checkEmail(email: String){
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val response = repository.checkEmail(email)
                Log.d("Email: ","Response "+response)
                if (response.unique) {
                    _uiState.value = _uiState.value.copy(emailError = "", isEmailUnique = true)
                } else {
                    _uiState.value = _uiState.value.copy(emailError = "Email is already taken")
                }



            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(emailError = "An error occurred")
            }
            finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    fun checkUsername(username: String) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val response = repository.checkUsername(username)
                if (response.unique) {
                    _uiState.value = _uiState.value.copy(usernameError = "", isUsernameUnique = true)
                } else {
                    _uiState.value = _uiState.value.copy(usernameError = "Username is already taken")
                }
                Log.d("Username: ","Response "+response)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(usernameError = "An error occurred")
            }
            finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    fun signUp(email:String,password:String,username: String,contactInfo:String,pfp:String,organization: String) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val response = repository.createUser(CreateUserRequest(username,email,password,contactInfo,pfp,organization))
                if (response.message == "User created successfully") {
                    _uiState.value = _uiState.value.copy(signUpError = "", signUpSuccess = true)
                } else {
                    _uiState.value = _uiState.value.copy(signUpError = response.message)
                }
                Log.d("SignUp","Response: "+response)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(signUpError = "SignUp Failed! Check Internet Connection")
            }
            finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
//    fun showToast(context: Context, message: String) {
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//    }
    fun emptyError()
    {
        _uiState.value = _uiState.value.copy(emailError = "")
        _uiState.value = _uiState.value.copy(usernameError = "")
        _uiState.value = _uiState.value.copy(signUpError =  "")
    }
}

data class SignUpState(
    val isEmailUnique: Boolean = false,
    val emailError: String = "",
    val isUsernameUnique: Boolean = false,
    val usernameError: String = "",
    val signUpSuccess: Boolean = false,
    val signUpError: String = "",
    val isLoading: Boolean = false
)
