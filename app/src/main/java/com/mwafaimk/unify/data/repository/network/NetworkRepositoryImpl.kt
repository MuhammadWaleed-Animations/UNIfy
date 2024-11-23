package com.mwafaimk.unify.data.repository.network

import com.mwafaimk.unify.core.util.datastore.AuthStateManager
import com.mwafaimk.unify.data.model.post.PostDetails
import com.mwafaimk.unify.data.model.post.postReport.ReportPostResponse
import com.mwafaimk.unify.data.model.user.createUser.CreateUserRequest
import com.mwafaimk.unify.data.model.user.createUser.CreateUserResponse
import com.mwafaimk.unify.data.model.user.login.LoginRequest
import com.mwafaimk.unify.data.model.user.login.LoginResponse
import com.mwafaimk.unify.data.network.ApiService

import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
//    private val authStateManager: AuthStateManager
): NetworkRepository {

    override suspend fun createUser(request: CreateUserRequest): CreateUserResponse {
        return apiService.createUser(request)
    }

    override suspend fun loginUser(request: LoginRequest): LoginResponse {
        return apiService.loginUser(request)
    }

    override suspend fun getAllPosts(): List<PostDetails> {
        return apiService.getAllPosts()
    }

    override suspend fun reportPost(postId: String): ReportPostResponse {
        return apiService.reportPost(postId)
    }
}