package com.mwafaimk.unify.data.repository.network

import com.mwafaimk.unify.core.util.datastore.AuthStateManager
import com.mwafaimk.unify.data.model.admin.AdminDetails
import com.mwafaimk.unify.data.model.blockedUser.BlockUserRequest
import com.mwafaimk.unify.data.model.blockedUser.BlockUserResponse
import com.mwafaimk.unify.data.model.blockedUser.GetAllBlockedUsersResponse
import com.mwafaimk.unify.data.model.blockedUser.RemoveBlockedUserRequest
import com.mwafaimk.unify.data.model.blockedUser.RemoveBlockedUserResponse
import com.mwafaimk.unify.data.model.post.PostDetails
import com.mwafaimk.unify.data.model.post.postReport.ReportPostResponse
import com.mwafaimk.unify.data.model.user.User
import com.mwafaimk.unify.data.model.user.createUser.CreateUserRequest
import com.mwafaimk.unify.data.model.user.createUser.CreateUserResponse
import com.mwafaimk.unify.data.model.user.deleteUser.DeleteUserResponse
import com.mwafaimk.unify.data.model.user.login.LoginRequest
import com.mwafaimk.unify.data.model.user.login.LoginResponse
import com.mwafaimk.unify.data.model.user.updateUser.UpdateUserRequest
import com.mwafaimk.unify.data.model.user.updateUser.UpdateUserResponse
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

    override suspend fun getUser(request: String): User {
        return apiService.getUser(request)
    }

    override suspend fun updateUser(userId: String,request: UpdateUserRequest): UpdateUserResponse {
        return apiService.updateUser(userId, request)
    }

    override suspend fun deleteUser(userId: String): DeleteUserResponse {
        return apiService.deleteUser(userId)
    }


    override suspend fun getAllPosts(): List<PostDetails> {
        return apiService.getAllPosts()
    }

    override suspend fun reportPost(postId: String): ReportPostResponse {
        return apiService.reportPost(postId)
    }


    override suspend fun blockUser(request: BlockUserRequest): BlockUserResponse {
        return apiService.addBlockedUser(request)
    }

    override suspend fun removeBlockedUser(request: RemoveBlockedUserRequest): RemoveBlockedUserResponse {
        return apiService.removeBlockedUser(request)
    }
    override suspend fun getAllBlockedUsers(request: AdminDetails): GetAllBlockedUsersResponse {
        return apiService.getAllBlockedUsers(request)
    }

}