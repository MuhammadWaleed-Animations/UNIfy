package com.mwafaimk.unify.data.model.post.done

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ToggleDoneRequest(
    @SerialName("done") val done: Boolean,
)
