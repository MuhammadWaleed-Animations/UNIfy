package com.mwafaimk.unify.data.model.post.delete

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeletePostResponse(
    @SerialName("message") val message: String,
)