package com.mwafaimk.unify.data.model.blockedUser

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlockUserResponse(
    @SerialName("message") val message: String,
    @SerialName("blockedUser") val blockedUser: BlockedUserDetails,
)

