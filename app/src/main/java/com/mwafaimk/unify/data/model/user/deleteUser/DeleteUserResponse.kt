package com.mwafaimk.unify.data.model.user.deleteUser

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteUserResponse(
    @SerialName("message") val message: String,
)