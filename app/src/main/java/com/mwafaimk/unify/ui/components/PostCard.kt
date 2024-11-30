package com.mwafaimk.unify.ui.components
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mwafaimk.unify.R


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mwafaimk.unify.data.model.post.PostDetails

@Composable
fun PostCard(postData: PostDetails) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp) // Padding for the content
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ProfileSection(postData)
                    Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(8.dp))
                EventDetails(postData)
                Spacer(modifier = Modifier.height(8.dp))
                AdditionalInfo(postData)
            }

            // Flag button at the top-right
            TextButton(
                onClick = {
                    // Handle flag click here
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                Text(
                    text = "🚩",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Link button at the bottom-right
            LinkButton(
                linkToCopy = postData.contactInfo?: "I am Batman",
                modifier = Modifier
                    .align(Alignment.BottomEnd) // Correct alignment inside Box
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun LinkButton(linkToCopy: String, modifier: Modifier = Modifier) {
    val clipboardManager = LocalClipboardManager.current

    TextButton(
        onClick = {
            clipboardManager.setText(AnnotatedString(linkToCopy)) // Copies link to clipboard
        },
        modifier = modifier // Modifier passed from the caller
    ) {
        Text(
            text = "\uD83D\uDD17", // Unicode for 🔗
            fontSize = 16.sp,
            color = Color.Red // Sets the icon/text color
        )
    }
}
