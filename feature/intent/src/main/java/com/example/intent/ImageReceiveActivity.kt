package com.example.intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.ReviewTheme
import com.example.ui.EmptyStateScreen
import com.example.ui.ImageDisplayCard
import com.example.ui.ScreenScaffold

class ImageReceiveActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Intent로 받은 이미지 URI
        val imageUri =
            when {
                intent?.action == Intent.ACTION_SEND -> {
                    intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
                }

                else -> {
                    null
                }
            }

        setContent {
            ReviewTheme {
                ImageReceiveScreen(
                    imageUri = imageUri,
                    onBackClick = { finish() },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageReceiveScreen(
    imageUri: Uri?,
    onBackClick: () -> Unit,
) {
    ScreenScaffold(
        title = "이미지 공유받기",
        onBackClick = onBackClick,
    ) { padding ->
        if (imageUri != null) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ImageDisplayCard(
                    imageUri = imageUri,
                    description = "공유받은 이미지",
                    showUri = true,
                    modifier = Modifier.weight(1f),
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onBackClick,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("닫기")
                }
            }
        } else {
            EmptyStateScreen(
                emoji = "🖼️",
                title = "이미지가 없습니다",
                description = "공유된 이미지를 찾을 수 없습니다",
                actionText = "닫기",
                onActionClick = onBackClick,
                modifier = Modifier.padding(padding),
            )
        }
    }
}
