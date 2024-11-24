package com.mwafaimk.unify.data.model.post.createPost

import com.mwafaimk.unify.data.model.post.PostDetails
import com.mwafaimk.unify.data.model.post.updatePost.UpdatePostDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatePostResponse(
    @SerialName("message") val message: String,
    @SerialName("post") val post: UpdatePostDetails,
)
