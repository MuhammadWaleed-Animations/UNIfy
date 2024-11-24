package com.mwafaimk.unify.ui.components

import androidx.annotation.DrawableRes

data class PostData(
    @DrawableRes val profileImage: Int,
    val userName: String,
    val userEmail: String,
    val eventTitle: String,
    val eventDescription: String,
    val eventTime: String,
    val additionalInfo: String
)