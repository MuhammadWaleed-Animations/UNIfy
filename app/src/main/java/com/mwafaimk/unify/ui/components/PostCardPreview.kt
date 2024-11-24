package com.mwafaimk.unify.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mwafaimk.unify.R


@Preview(showBackground = true)
@Composable
fun PostCardPreview() {
    val postData = PostData(
        profileImage = R.drawable.cat1,
        userName = "Meow",
        userEmail = "l226824@lhr.nu.edu.pk",
        eventTitle = "Anime Watch Party",
        eventDescription = "Watching Haikyu in CS13\nMaray lea bhi koi Snacks pakr lana",
        eventTime = "3:30 - 4:50",
        additionalInfo = "Jitnay zyada utna acha"
    )
    MaterialTheme {
        PostCard(postData = postData)
    }
}