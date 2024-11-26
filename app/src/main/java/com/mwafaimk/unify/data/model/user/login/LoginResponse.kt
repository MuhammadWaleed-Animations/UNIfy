package com.mwafaimk.unify.data.model.user.login

import com.mwafaimk.unify.data.model.user.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("user") val user: User? = null,
    @SerialName("isAdmin") val isAdmin: Boolean? = null,
    @SerialName("message") val message: String? = null

)

