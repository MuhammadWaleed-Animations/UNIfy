package com.mwafaimk.unify.data.repository.network

import com.mwafaimk.unify.data.model.user.User
import com.mwafaimk.unify.data.model.user.checkUsername.CheckUsernameResponse
import com.mwafaimk.unify.data.model.user.createUser.CreateUserRequest
import com.mwafaimk.unify.data.model.user.createUser.CreateUserResponse
import com.mwafaimk.unify.data.model.user.deleteUser.DeleteUserResponse
import com.mwafaimk.unify.data.model.user.login.LoginRequest
import com.mwafaimk.unify.data.model.user.login.LoginResponse
import com.mwafaimk.unify.data.model.user.updateUser.UpdateUserRequest
import com.mwafaimk.unify.data.model.user.updateUser.UpdateUserResponse
import retrofit2.http.Path

interface UserNetworkRepository {
    suspend fun createUser(request: CreateUserRequest): CreateUserResponse
    suspend fun loginUser(request: LoginRequest): LoginResponse
    suspend fun getUser(request: String): User
    suspend fun updateUser(userId: String,request: UpdateUserRequest): UpdateUserResponse
    suspend fun deleteUser(userId: String): DeleteUserResponse
    suspend fun checkUsername(username: String): CheckUsernameResponse
    suspend fun checkEmail(email: String): CheckUsernameResponse

}