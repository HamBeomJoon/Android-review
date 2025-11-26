package com.example.ui

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

/**
 * 이미지 표시 카드
 *
 * @param imageUri 표시할 이미지 URI
 * @param description 이미지 설명 (optional)
 * @param showUri URI 표시 여부 (default: true)
 */
@Composable
fun ImageDisplayCard(
    imageUri: Uri?,
    modifier: Modifier = Modifier,
    description: String? = null,
    showUri: Boolean = true,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = false),
        ) {
            if (imageUri != null) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = description ?: "이미지",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit,
                )
            } else {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "이미지 없음",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }

        if (showUri && imageUri != null) {
            Text(
                text = "URI: $imageUri",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
