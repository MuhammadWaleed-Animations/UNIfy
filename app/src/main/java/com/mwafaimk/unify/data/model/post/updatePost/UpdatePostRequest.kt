package com.mwafaimk.unify.data.model.post.updatePost

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdatePostRequest(
    @SerialName("title") val title: String?=null,
    @SerialName("description") val description: String?=null,
    @SerialName("contactInfo") val contactInfo: String?=null,
    @SerialName("time") val time: String?=null,
    @SerialName("memberCount") val memberCount: String?=null,
    @SerialName("category") val category: List<String>?=null,
    @SerialName("location") val location: String?=null,
)