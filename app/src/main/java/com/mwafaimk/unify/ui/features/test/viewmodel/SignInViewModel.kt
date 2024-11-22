package com.mwafaimk.unify.ui.features.test.viewmodel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwafaimk.unify.data.model.user.login.LoginResponse
import com.mwafaimk.unify.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response: LoginResponse = userRepository.login(email, password)
                Log.d("API chal gyaaaaaaaaa", "signIn: "+ response)
            } catch (e: Exception) {
                Log.d("API nhi chalaaaa","Sign in  "+ e)
            }
        }
    }
}