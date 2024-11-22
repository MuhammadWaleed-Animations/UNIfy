package com.mwafaimk.unify.data.model.post.postReport

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReportPostResponse(
    @SerialName("message") val message: String,
    @SerialName("post") val post: PostReportStatus,
)