package com.mwafaimk.unify.data.network.apiServices

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
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PostApiService {
    // Post APIs
    @GET("/posts/all")
    suspend fun getAllPosts(@Query("organization") organization: String): List<PostDetails>
    @PUT("/posts/{postId}/report")
    suspend fun reportPost(@Path("postId") postId: String): ReportPostResponse
    @PUT("/posts/{postId}/done")
    suspend fun togglePostDone(@Path("postId") postId: String, @Body request: ToggleDoneRequest): ToggleDoneResponse
    @DELETE("/posts/user/{userId}")
    suspend fun deleteUserPosts(@Path("userId") userId: String): DeletePostResponse
    @DELETE("/posts/{postId}")
    suspend fun deletePost(@Path("postId") postId: String): DeletePostResponse
    @PUT("/posts/{postId}")
    suspend fun updatePost(@Path("postId") postId: String, @Body updates: UpdatePostRequest): UpdatePostResponse  ////////////make it work //don't worry past me i got ya. I resolved the bug :D
    @POST("/posts")
    suspend fun createPost(@Body request: CreatePostRequest): CreatePostResponse
    @DELETE("/posts")
    suspend fun deleteAllPosts(): DeletePostResponse
    @GET("/posts/category")
    suspend fun getPostsByCategory(@Query("category") category: String, @Query("organization") organization: String): List<PostDetails>
    @GET("/posts/user/{userId}")
    suspend fun getUserPosts(@Path("userId") userId: String): List<UpdatePostDetails>
    @POST("/posts/reported")
    suspend fun getReportedPosts(@Body request: AdminDetails): List<PostDetails>
    @PUT("/posts/{postId}/unreport")
    suspend fun unReportPost(@Path("postId") postId: String, @Body request: AdminDetails): ReportPostResponse

}