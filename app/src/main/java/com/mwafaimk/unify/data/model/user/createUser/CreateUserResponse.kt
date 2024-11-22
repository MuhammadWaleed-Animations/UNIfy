package com.mwafaimk.unify.data.model.user.createUser

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserResponse(
    @SerialName("message") val message: String,
    @SerialName("userId") val userId: String,
)