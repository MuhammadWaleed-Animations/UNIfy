package com.mwafaimk.unify.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.mwafaimk.unify.R

@Composable
fun PfpSelectionDialog(
    onDismissRequest: () -> Unit,
    onImageSelected: (String) -> Unit // Returns the selected image description
) {
    val pfpImages = listOf(
        Pair(R.drawable.cat1, "Cat 1"),
        Pair(R.drawable.cat2, "Cat 2"),
        Pair(R.drawable.cat3, "Cat 3"),
        Pair(R.drawable.cat4, "Cat 4")
    )

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    text = "Choose Your Profile Picture",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Grid of PFPs
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.height(250.dp),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(pfpImages) { pfp ->
                        Column(
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable {
                                    onImageSelected(pfp.second) // Return the description of the selected image
                                    onDismissRequest()
                                },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = pfp.first, // Load the drawable resource
                                contentDescription = pfp.second,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = pfp.second,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Dismiss Button
                Text(
                    text = "Cancel",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .clickable { onDismissRequest() }
                        .padding(8.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

//
//@Preview(showBackground = true)
//@Composable
//fun PfpSelectionDialogPreview() {
//    val showDialog = remember { mutableStateOf(true) }
//
//    if (showDialog.value) {
//        PfpSelectionDialog(
//            onDismissRequest = { showDialog.value = false },
//            onImageSelected = { selectedPfp ->
//                // Handle image selection, e.g., log the resource ID
//                println("Selected PFP: $selectedPfp")
//                showDialog.value = false
//            }
//        )
//    }
//}