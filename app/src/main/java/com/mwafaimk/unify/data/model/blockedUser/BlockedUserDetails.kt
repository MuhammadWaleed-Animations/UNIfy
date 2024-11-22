package com.mwafaimk.unify.data.model.blockedUser

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlockedUserDetails(
    @SerialName("email") val email: String,
    @SerialName("reason") val reason: String?,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("updatedAt") val updatedAt: String,
)