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
import com.mwafaimk.unify.data.model.user.createUser.CreateUserRequest
import com.mwafaimk.unify.data.model.user.createUser.CreateUserResponse
import com.mwafaimk.unify.data.model.user.deleteUser.DeleteUserResponse
import com.mwafaimk.unify.data.model.user.login.LoginRequest
import com.mwafaimk.unify.data.model.user.login.LoginResponse
import com.mwafaimk.unify.data.model.user.updateUser.UpdateUserRequest
import com.mwafaimk.unify.data.model.user.updateUser.UpdateUserResponse
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Query


interface NetworkRepository {
    suspend fun createUser(request: CreateUserRequest): CreateUserResponse
    suspend fun loginUser(request: LoginRequest): LoginResponse
    suspend fun getUser(request: String): User
    suspend fun updateUser(userId: String,request: UpdateUserRequest):UpdateUserResponse
    suspend fun deleteUser(userId: String):DeleteUserResponse

    suspend fun getAllPosts(organization: String): List<PostDetails>
    suspend fun reportPost(postId: String): ReportPostResponse
    suspend fun togglePostDone(postId: String, request: ToggleDoneRequest): ToggleDoneResponse
    suspend fun deleteUserPosts(userId: String): DeletePostResponse
    suspend fun deletePost(postId: String): DeletePostResponse
    suspend fun updatePost(postId: String, updates: UpdatePostRequest): UpdatePostResponse
    suspend fun createPost(request: CreatePostRequest):CreatePostResponse
    suspend fun deleteAllPosts(): DeletePostResponse
    suspend fun getPostsByCategory(category: String,organization: String): List<PostDetails>
    suspend fun getUserPosts(userId: String): List<UpdatePostDetails>
    suspend fun getReportedPosts(request: AdminDetails): List<PostDetails>
    suspend fun unReportPost(postId: String,request: AdminDetails): ReportPostResponse





    suspend fun blockUser(request: BlockUserRequest):BlockUserResponse
    suspend fun removeBlockedUser(request: RemoveBlockedUserRequest):RemoveBlockedUserResponse
    suspend fun getAllBlockedUsers(request: AdminDetails):GetAllBlockedUsersResponse
}
