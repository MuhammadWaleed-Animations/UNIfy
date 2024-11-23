package com.mwafaimk.unify.data.model.blockedUser

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlockUserRequest(
    @SerialName("email") val email: String,
    @SerialName("reason") val reason: String? = null,
    @SerialName("userId") val userId:String //admin ki  userId
)

