package com.mwafaimk.unify.ui.pages.test.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwafaimk.unify.core.util.datastore.DataManager
import com.mwafaimk.unify.data.model.user.login.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreTestViewModel @Inject constructor(
    private val dataManager: DataManager
) : ViewModel() {

    private val _userState = MutableStateFlow<LoginResponse?>(null)
    val userState: StateFlow<LoginResponse?> = _userState

    init {
        // Observe user data from DataManager
        viewModelScope.launch {
            dataManager.userFlow.collectLatest { user ->
                _userState.value = user
            }
        }
    }

    // Save user data
    fun saveUser(user: LoginResponse) {
        viewModelScope.launch {
            dataManager.saveUser(user)
        }
    }

    // Delete user data
    fun deleteUser() {
        viewModelScope.launch {
            dataManager.deleteUser()
        }
    }
}
