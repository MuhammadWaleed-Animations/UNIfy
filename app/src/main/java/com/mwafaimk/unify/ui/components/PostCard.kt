package com.mwafaimk.unify.ui.components

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mwafaimk.unify.R


@Composable
fun PostCard(postData: PostData) {
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
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ProfileSection(postData)
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.flag),
                    contentDescription = "Flag Icon",
                    tint = Color.Red,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            EventDetails(postData)
            Spacer(modifier = Modifier.height(8.dp))
            AdditionalInfo(postData)
        }
    }
}




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
