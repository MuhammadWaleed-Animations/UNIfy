package com.mwafaimk.unify.core.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mwafaimk.unify.R


@Composable
fun Boolean.toInt(): Int {
    return if (this) 1 else 0
}
