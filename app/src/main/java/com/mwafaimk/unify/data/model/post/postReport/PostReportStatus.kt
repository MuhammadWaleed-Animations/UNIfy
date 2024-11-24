package com.mwafaimk.unify.data.model.post.postReport

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PostReportStatus(
    @SerialName("_id") val _id: String,
    @SerialName("reported") val reported: Boolean,
)
