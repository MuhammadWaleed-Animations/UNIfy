package com.mwafaimk.unify.data.repository.network

import com.mwafaimk.unify.data.model.admin.AdminDetails
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

interface PostNetworkRepository {
    suspend fun getAllPosts(organization: String): List<PostDetails>
    suspend fun reportPost(postId: String): ReportPostResponse
    suspend fun togglePostDone(postId: String, request: ToggleDoneRequest): ToggleDoneResponse
    suspend fun deleteUserPosts(userId: String): DeletePostResponse
    suspend fun deletePost(postId: String): DeletePostResponse
    suspend fun updatePost(postId: String, updates: UpdatePostRequest): UpdatePostResponse
    suspend fun createPost(request: CreatePostRequest): CreatePostResponse
    suspend fun deleteAllPosts(): DeletePostResponse
    suspend fun getPostsByCategory(category: String,organization: String): List<PostDetails>
    suspend fun getUserPosts(userId: String): List<UpdatePostDetails>
    suspend fun getReportedPosts(request: AdminDetails): List<PostDetails>
    suspend fun unReportPost(postId: String,request: AdminDetails): ReportPostResponse
}