package com.example.intent

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.ui.SectionCard

@Composable
fun IntentScreen() {
    val context = LocalContext.current

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Intent 학습",
            style = MaterialTheme.typography.headlineSmall,
        )

        HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

        SectionCard(
            title = "1. 명시적 Intent (Explicit)",
            description = "특정 컴포넌트를 직접 지정",
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ) {
            Button(
                onClick = {
                    val intent =
                        Intent(context, SecondActivity::class.java).apply {
                            putExtra("message", "명시적 Intent로 전달된 메시지")
                            putExtra("number", 12345)
                        }
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("SecondActivity 열기")
            }
        }

        SectionCard(
            title = "2. 암시적 Intent (Implicit)",
            description = "Action을 지정하고 시스템이 처리",
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                OutlinedButton(
                    onClick = {
                        val intent =
                            Intent(Intent.ACTION_VIEW).apply {
                                data = "https://developer.android.com".toUri()
                            }
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f),
                ) {
                    Text("🌐 웹")
                }

                OutlinedButton(
                    onClick = {
                        val intent =
                            Intent(Intent.ACTION_DIAL).apply {
                                data = "tel:01012345678".toUri()
                            }
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f),
                ) {
                    Text("📞 전화")
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                OutlinedButton(
                    onClick = {
                        val intent =
                            Intent(Intent.ACTION_SENDTO).apply {
                                data = "mailto:".toUri()
                                putExtra(Intent.EXTRA_EMAIL, arrayOf("test@example.com"))
                                putExtra(Intent.EXTRA_SUBJECT, "안녕하세요")
                            }
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f),
                ) {
                    Text("✉️ 메일")
                }

                OutlinedButton(
                    onClick = {
                        val intent =
                            Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, "Intent 공유 테스트!")
                            }
                        context.startActivity(Intent.createChooser(intent, "공유하기"))
                    },
                    modifier = Modifier.weight(1f),
                ) {
                    Text("📤 공유")
                }
            }
        }

        SectionCard(
            title = "3. 이미지 공유받기",
            description = "브라우저에서 이미지 공유 시 이 앱으로 받기",
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ) {
            Button(
                onClick = {
                    val intent =
                        Intent(context, ImageReceiveActivity::class.java).apply {
                            action = Intent.ACTION_SEND
                            putExtra(
                                Intent.EXTRA_STREAM,
                                "https://images.unsplash.com/photo-1493612276216-ee3925520721".toUri(),
                            )
                        }
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("이미지 공유 테스트")
            }
        }
    }
}
