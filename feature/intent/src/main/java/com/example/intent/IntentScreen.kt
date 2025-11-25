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
            style = MaterialTheme.typography.headlineLarge,
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
                    text = "1️⃣ 명시적 Intent (Explicit)",
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
                    text = "2️⃣ 암시적 Intent (Implicit)",
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
                    // 웹 브라우저 열기
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

                    // 전화 걸기
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
                    // 이메일 보내기
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

                    // 공유하기
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

        // 📌 3. Intent Flags
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors =
                CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                ),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "3️⃣ Intent Flags",
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "Activity 스택 관리",
                    style = MaterialTheme.typography.bodySmall,
                )

                Button(
                    onClick = {
                        val intent =
                            Intent(context, SecondActivity::class.java).apply {
                                flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                            }
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("NEW_TASK + CLEAR_TOP")
                }
            }
        }

        // 결과 표시
        if (resultText.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors =
                    CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
            ) {
                Text(
                    text = resultText,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
