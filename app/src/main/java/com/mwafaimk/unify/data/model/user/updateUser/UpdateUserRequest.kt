package com.mwafaimk.unify.data.model.user.updateUser

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequest(
    @SerialName("username") val username: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("contactInfo") val contactInfo: String? = null,
    @SerialName("profilePicture") val profilePicture: String? = null,
    @SerialName("organization") val organization: String? = null,
)
