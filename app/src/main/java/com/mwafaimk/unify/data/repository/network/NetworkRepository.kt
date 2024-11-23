package com.mwafaimk.unify.data.repository.network

import com.mwafaimk.unify.data.model.post.PostDetails
import com.mwafaimk.unify.data.model.post.postReport.ReportPostResponse
import com.mwafaimk.unify.data.model.user.createUser.CreateUserRequest
import com.mwafaimk.unify.data.model.user.createUser.CreateUserResponse
import com.mwafaimk.unify.data.model.user.login.LoginRequest
import com.mwafaimk.unify.data.model.user.login.LoginResponse


interface NetworkRepository {
    suspend fun createUser(request: CreateUserRequest): CreateUserResponse
    suspend fun loginUser(request: LoginRequest): LoginResponse
    suspend fun getAllPosts(): List<PostDetails>
    suspend fun reportPost(postId: String): ReportPostResponse
}
