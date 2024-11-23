package com.mwafaimk.unify.data.network

import com.mwafaimk.unify.data.model.blockedUser.BlockUserRequest
import com.mwafaimk.unify.data.model.blockedUser.BlockUserResponse
import com.mwafaimk.unify.data.model.blockedUser.GetAllBlockedUsersResponse
import com.mwafaimk.unify.data.model.blockedUser.RemoveBlockedUserRequest
import com.mwafaimk.unify.data.model.blockedUser.RemoveBlockedUserResponse
import com.mwafaimk.unify.data.model.post.PostDetails
import com.mwafaimk.unify.data.model.post.createPost.CreatePostRequest
import com.mwafaimk.unify.data.model.post.createPost.CreatePostResponse
import com.mwafaimk.unify.data.model.post.delete.DeletePostResponse
import com.mwafaimk.unify.data.model.post.done.ToggleDoneRequest
import com.mwafaimk.unify.data.model.post.done.ToggleDoneResponse
import com.mwafaimk.unify.data.model.post.postReport.ReportPostResponse
import com.mwafaimk.unify.data.model.user.createUser.CreateUserRequest
import com.mwafaimk.unify.data.model.user.createUser.CreateUserResponse
import com.mwafaimk.unify.data.model.user.deleteUser.DeleteUserResponse
import com.mwafaimk.unify.data.model.user.login.LoginRequest
import com.mwafaimk.unify.data.model.user.login.LoginResponse
import com.mwafaimk.unify.data.model.user.updateUser.UpdateUserRequest
import com.mwafaimk.unify.data.model.user.updateUser.UpdateUserResponse


import com.mwafaimk.unify.data.model.user.User
import retrofit2.http.*

interface ApiService {

    // User APIs
    @POST("/users")
    suspend fun createUser(@Body request: CreateUserRequest): CreateUserResponse

    @POST("/users/login")
    suspend fun loginUser(@Body request: LoginRequest): LoginResponse

    @GET("/users/{userId}")
    suspend fun getUser(@Path("userId") userId: String, @Header("Authorization") token: String): User

    @PUT("/users/{userId}")
    suspend fun updateUser(
        @Path("userId") userId: String,
        @Header("Authorization") token: String,
        @Body request: UpdateUserRequest
    ): UpdateUserResponse

    @DELETE("/users/{userId}")
    suspend fun deleteUser(
        @Path("userId") userId: String,
        @Header("Authorization") token: String
    ): DeleteUserResponse

    // Post APIs
    @POST("/posts")
    suspend fun createPost(@Body request: CreatePostRequest): CreatePostResponse

    @GET("/posts/all")
    suspend fun getAllPosts(@Query("organization") organization: String? = null): List<PostDetails>

    @PUT("/posts/{postId}/report")
    suspend fun reportPost(@Path("postId") postId: String): ReportPostResponse

    @PUT("/posts/{postId}/done")
    suspend fun togglePostDone(
        @Path("postId") postId: String,
        @Body request: ToggleDoneRequest
    ): ToggleDoneResponse

    @GET("/posts/user/{userId}")
    suspend fun getUserPosts(@Path("userId") userId: String): List<PostDetails>

    @DELETE("/posts/{postId}")
    suspend fun deletePost(@Path("postId") postId: String): DeletePostResponse

    @DELETE("/posts/user/{userId}")
    suspend fun deleteUserPosts(@Path("userId") userId: String): DeletePostResponse

    @GET("/posts/category")
    suspend fun getPostsByCategory(@Query("category") category: String): List<PostDetails>

//    // Admin APIs
//    @POST("/admin/create")
//    suspend fun createAdmin(@Body request: AdminCreateRequest): AdminCreateResponse
//
//    @GET("/admin")
//    suspend fun getAllAdmins(): List<AdminDetails>

    // Blocked User APIs
    @POST("/blocked-users/add")
    suspend fun addBlockedUser(@Body request: BlockUserRequest): BlockUserResponse

    @POST("/blocked-users/remove")
    suspend fun removeBlockedUser(@Body request: RemoveBlockedUserRequest): RemoveBlockedUserResponse

    @GET("/blocked-users")
    suspend fun getAllBlockedUsers(): GetAllBlockedUsersResponse
}