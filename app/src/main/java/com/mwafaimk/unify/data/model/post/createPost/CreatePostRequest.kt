package com.mwafaimk.unify.data.model.post.createPost

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatePostRequest(
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("contactInfo") val contactInfo: String?,
    @SerialName("time") val time: String?,
    @SerialName("memberCount") val memberCount: String?,
    @SerialName("category") val category: List<String>,
    @SerialName("location") val location: String,
    @SerialName("userId") val userId: String,
)

