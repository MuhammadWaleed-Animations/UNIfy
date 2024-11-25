package com.mwafaimk.unify.data.repository.network

import com.mwafaimk.unify.data.model.admin.AdminDetails
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
import com.mwafaimk.unify.data.model.post.updatePost.UpdatePostDetails
import com.mwafaimk.unify.data.model.post.updatePost.UpdatePostRequest
import com.mwafaimk.unify.data.model.post.updatePost.UpdatePostResponse
import com.mwafaimk.unify.data.model.user.User
import com.mwafaimk.unify.data.model.user.checkUsername.CheckUsernameResponse
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

    //User Api
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

    override suspend fun checkUsername(username: String): CheckUsernameResponse {
        return apiService.checkUsername(username)
    }









    //POST API
    override suspend fun getAllPosts(organization: String): List<PostDetails> {
        return apiService.getAllPosts(organization)
    }
    override suspend fun reportPost(postId: String): ReportPostResponse {
        return apiService.reportPost(postId)
    }
    override suspend fun togglePostDone(postId: String, request: ToggleDoneRequest): ToggleDoneResponse {
        return apiService.togglePostDone(postId,request)
    }
    override suspend fun deleteUserPosts(userId: String): DeletePostResponse {
        return apiService.deleteUserPosts(userId)
    }
    override suspend fun deletePost(postId: String): DeletePostResponse {
        return apiService.deletePost(postId)
    }
    override suspend fun updatePost( postId: String, updates: UpdatePostRequest): UpdatePostResponse {
        return apiService.updatePost(postId,updates)
    }
    override suspend fun createPost(request: CreatePostRequest): CreatePostResponse {
        return apiService.createPost(request)
    }
    override suspend fun deleteAllPosts(): DeletePostResponse {
        return apiService.deleteAllPosts()
    }
    override suspend fun getPostsByCategory(category: String,organization: String): List<PostDetails> {
        return apiService.getPostsByCategory(category, organization)
    }
    override suspend fun getUserPosts(userId: String): List<UpdatePostDetails> {
        return apiService.getUserPosts(userId)
    }
    override suspend fun getReportedPosts(request: AdminDetails): List<PostDetails> {
        return apiService.getReportedPosts(request)
    }
    override suspend fun unReportPost(postId: String, request: AdminDetails): ReportPostResponse {
        return apiService.unReportPost(postId,request)
    }





    //blockedUser
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