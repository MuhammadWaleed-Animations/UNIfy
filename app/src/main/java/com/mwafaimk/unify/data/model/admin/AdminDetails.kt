package com.mwafaimk.unify.data.model.admin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdminDetails(
    @SerialName("userId") val userId: String
)
