package com.mwafaimk.unify.data.model.post.done

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PostDoneStatus(
    @SerialName("_id") val id: String,
    @SerialName("done") val done: Boolean,
)
