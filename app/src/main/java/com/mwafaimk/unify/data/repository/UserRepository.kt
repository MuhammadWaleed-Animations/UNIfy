package com.mwafaimk.unify.data.repository

import com.mwafaimk.unify.data.model.user.login.LoginRequest
import com.mwafaimk.unify.data.network.ApiService
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun login(email: String, password: String) =
        apiService.loginUser(LoginRequest(email, password))
}