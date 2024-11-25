package com.mwafaimk.unify.data.model.user.checkUsername

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckUsernameResponse(
    @SerialName("unique") val unique: Boolean,      // True if the username is available
    @SerialName("message") val message: String       // Descriptive message about the username status
)