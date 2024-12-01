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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mwafaimk.unify.data.model.post.PostDetails

@Composable
fun PostCard(
    postData: PostDetails,
    onReport: (String) -> Unit = {}, // Takes the report reason
    onProfileClick: () -> Unit = {}
) {
    var showReportDialog by remember { mutableStateOf(false) }
    var reportReason by remember { mutableStateOf("") }

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ProfileSection(postData, onProfileClick = onProfileClick)
                    Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(8.dp))
                EventDetails(postData)
                Spacer(modifier = Modifier.height(8.dp))
                AdditionalInfo(postData)
            }

            // Flag button at the top-right
            TextButton(
                onClick = { showReportDialog = true },
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
                linkToCopy = postData.contactInfo ?: "I am Batman",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
            )
        }

        // Report Dialog
        if (showReportDialog) {
            AlertDialog(
                onDismissRequest = { showReportDialog = false },
                title = { Text("Report Post") },
                text = {
                    Column {
                        Text("Enter the reason for reporting this post:")
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = reportReason,
                            onValueChange = { reportReason = it },
                            placeholder = { Text("Reason") }
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            if (reportReason.isNotBlank()) {
                                onReport(reportReason) // Pass the reason to the ViewModel
                                showReportDialog = false
                            }
                        }
                    ) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showReportDialog = false }) {
                        Text("Cancel")
                    }
                }
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
