package com.mwafaimk.unify.data.model.user.createUser

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    @SerialName("username") val username: String,
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("contactInfo") val contactInfo: String?,
    @SerialName("pfp") val pfp: String?,
    @SerialName("organization") val organization: String?,
)