package com.example.pacelable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.UserParcelable
import com.example.model.UserSerializable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    type: String,
    userParcelable: UserParcelable?,
    userSerializable: UserSerializable?,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("데이터 수신 결과") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, "뒤로가기")
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            when (type) {
                "Parcelable" -> {
                    ParcelableResultContent(user = userParcelable)
                }

                "Serializable" -> {
                    SerializableResultContent(user = userSerializable)
                }
            }
        }
    }
}

@Composable
fun ParcelableResultContent(user: UserParcelable?) {
    Text(
        text = "📦 Parcelable로 전송됨",
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
    )

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
                text = "✨ 특징",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )

            Text(
                text =
                    """
• Android 전용 인터페이스
• 더 빠른 성능 (약 10배)
• 메모리 효율적
• @Parcelize 어노테이션으로 간편 구현
• 리플렉션 미사용
                    """.trimIndent(),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
    }

    if (user != null) {
        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "📋 수신된 데이터",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                UserDataRow(label = "ID", value = user.id.toString())
                UserDataRow(label = "이름", value = user.name)
                UserDataRow(label = "이메일", value = user.email)
                UserDataRow(label = "나이", value = "${user.age}세")
                UserDataRow(label = "주소", value = user.address)
            }
        }

        CodeExampleCard(
            title = "💻 코드 예시",
            code =
                """
@Parcelize
data class User(
    val id: Int,
    val name: String,
    val email: String
) : Parcelable

// 사용
intent.putExtra("user", user)
                """.trimIndent(),
        )
    }
}

@Composable
fun SerializableResultContent(user: UserSerializable?) {
    Text(
        text = "📋 Serializable로 전송됨",
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
    )

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
                text = "✨ 특징",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
            )

            Text(
                text =
                    """
• Java 표준 인터페이스
• 구현이 매우 간단
• 성능이 느림 (리플렉션 사용)
• 플랫폼 독립적
• 파일 저장/네트워크 전송에 적합
                    """.trimIndent(),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
            )
        }
    }

    if (user != null) {
        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "📋 수신된 데이터",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                UserDataRow(label = "ID", value = user.id.toString())
                UserDataRow(label = "이름", value = user.name)
                UserDataRow(label = "이메일", value = user.email)
                UserDataRow(label = "나이", value = "${user.age}세")
                UserDataRow(label = "주소", value = user.address)
            }
        }

        CodeExampleCard(
            title = "💻 코드 예시",
            code =
                """
data class User(
    val id: Int,
    val name: String,
    val email: String
) : Serializable

// 사용
intent.putExtra("user", user)
                """.trimIndent(),
        )
    }
}

@Composable
fun UserDataRow(
    label: String,
    value: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "$label:",
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp,
        )
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp,
        )
    }
}

@Composable
fun CodeExampleCard(
    title: String,
    code: String,
) {
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
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.small,
            ) {
                Text(
                    text = code,
                    fontSize = 12.sp,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                    modifier = Modifier.padding(12.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}
