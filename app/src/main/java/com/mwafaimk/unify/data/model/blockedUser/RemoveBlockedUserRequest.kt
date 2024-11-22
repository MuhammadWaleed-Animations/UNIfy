package com.mwafaimk.unify.data.model.blockedUser

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoveBlockedUserRequest(
    @SerialName("email") val email: String,
)