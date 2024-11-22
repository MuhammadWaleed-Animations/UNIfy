package com.mwafaimk.unify.data.model.blockedUser

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAllBlockedUsersResponse(
    @SerialName("blockedUsers") val blockedUsers: List<BlockedUserDetails>,
)
