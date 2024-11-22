package com.mwafaimk.unify.data.model.user.updateUser

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequest(
    @SerialName("username") val username: String?,
    @SerialName("email") val email: String?,
    @SerialName("contactInfo") val contactInfo: String?,
    @SerialName("organization") val organization: String?,
)
