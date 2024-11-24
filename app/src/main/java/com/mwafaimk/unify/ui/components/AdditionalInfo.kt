package com.mwafaimk.unify.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mwafaimk.unify.R

@Composable
fun AdditionalInfo(postData: PostData) {
    Row(
        modifier = Modifier.fillMaxWidth(),

        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = postData.eventTime,
            fontSize = 13.sp,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = postData.additionalInfo,
            fontSize = 13.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(20.dp))
        Icon(
            painter = painterResource(id = R.drawable.attached),
            contentDescription = "Refresh Icon",
            tint = Color.Black,
            modifier = Modifier.size(16.dp)
        )
    }
}
