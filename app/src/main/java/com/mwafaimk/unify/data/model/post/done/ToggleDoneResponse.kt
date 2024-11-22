package com.mwafaimk.unify.data.model.post.done

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ToggleDoneResponse(
    @SerialName("message") val message: String,
    @SerialName("post") val post: PostDoneStatus,
)