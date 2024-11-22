package com.mwafaimk.unify.data.model.user.login

import com.mwafaimk.unify.data.model.user.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("user") val user: User,
    @SerialName("isAdmin") val isAdmin: Boolean
)

