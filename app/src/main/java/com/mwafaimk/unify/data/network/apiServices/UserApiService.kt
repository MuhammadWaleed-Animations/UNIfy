package com.mwafaimk.unify.data.network.apiServices

import com.mwafaimk.unify.data.model.user.User
import com.mwafaimk.unify.data.model.user.checkUsername.CheckUsernameResponse
import com.mwafaimk.unify.data.model.user.createUser.CreateUserRequest
import com.mwafaimk.unify.data.model.user.createUser.CreateUserResponse
import com.mwafaimk.unify.data.model.user.deleteUser.DeleteUserResponse
import com.mwafaimk.unify.data.model.user.login.LoginRequest
import com.mwafaimk.unify.data.model.user.login.LoginResponse
import com.mwafaimk.unify.data.model.user.updateUser.UpdateUserRequest
import com.mwafaimk.unify.data.model.user.updateUser.UpdateUserResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApiService {
    // User APIs
    @POST("/users")
    suspend fun createUser(@Body request: CreateUserRequest): CreateUserResponse

    @POST("/users/login")
    suspend fun loginUser(@Body request: LoginRequest): LoginResponse

    @POST("/users/{userId}")
    suspend fun getUser(@Path("userId") userId: String): User


    @PUT("/users/{userId}")
    suspend fun updateUser(@Path("userId") userId: String, @Body request: UpdateUserRequest): UpdateUserResponse

    @DELETE("/users/{userId}")
    suspend fun deleteUser(@Path("userId") userId: String): DeleteUserResponse

    @GET("/users/check-username/{username}")
    suspend fun checkUsername(@Path("username") username: String): CheckUsernameResponse

}