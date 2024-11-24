package com.mwafaimk.unify.data.model.post.updatePost

import com.mwafaimk.unify.data.model.post.PostDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdatePostResponse(
    @SerialName("message") val message: String,
    @SerialName("post") val post: UpdatePostDetails
)