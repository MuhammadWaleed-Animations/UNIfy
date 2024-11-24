package com.mwafaimk.unify.data.model.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class UserIdDetails(
    @SerialName("_id") val _id: String, // Fix for nested ID mapping
    @SerialName("username") val username: String?,
    @SerialName("email") val email: String,
    @SerialName("profilePicture") val profilePicture: String?
)

