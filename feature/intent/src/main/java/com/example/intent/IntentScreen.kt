package com.example.intent

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

@Composable
fun IntentScreen() {
    val context = LocalContext.current
    var resultText by remember { mutableStateOf("") }

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

        Divider()

        // 📌 1. 명시적 Intent (Explicit Intent)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors =
                CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "1. 명시적 Intent (Explicit)",
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "특정 컴포넌트를 직접 지정",
                    style = MaterialTheme.typography.bodySmall,
                )

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
        }

        // 📌 2. 암시적 Intent (Implicit Intent)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors =
                CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "2. 암시적 Intent (Implicit)",
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "Action을 지정하고 시스템이 처리",
                    style = MaterialTheme.typography.bodySmall,
                )

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
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors =
                CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "3. 이미지 공유받기",
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "브라우저에서 이미지 공유 시 이 앱으로 받기",
                    style = MaterialTheme.typography.bodySmall,
                )

                Button(
                    onClick = {
                        val intent =
                            Intent(context, ImageReceiveActivity::class.java).apply {
                                action = Intent.ACTION_SEND
                                putExtra(
                                    Intent.EXTRA_STREAM,
                                    "https://images.unsplash.com/photo-1493612276216-ee3925520721?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cmFuZG9tfGVufDB8fDB8fHww"
                                        .toUri(),
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
}
