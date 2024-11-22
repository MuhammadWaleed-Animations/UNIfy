package com.mwafaimk.unify.data.model.user.updateUser

import com.mwafaimk.unify.data.model.user.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserResponse(
    @SerialName("message") val message: String,
    @SerialName("user") val user: User,
)