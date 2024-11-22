package com.mwafaimk.unify.data.model.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PostDetails(
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("contactInfo") val contactInfo: String?,
    @SerialName("time") val time: String?,
    @SerialName("memberCount") val memberCount: String?,
    @SerialName("category") val category: List<String>,
    @SerialName("location") val location: String,
    @SerialName("timestamp") val timestamp: String,
    @SerialName("userId") val userId: String,
    @SerialName("reported") val reported: Boolean,
    @SerialName("done") val done: Boolean,
    @SerialName("_id") val id: String,
)
