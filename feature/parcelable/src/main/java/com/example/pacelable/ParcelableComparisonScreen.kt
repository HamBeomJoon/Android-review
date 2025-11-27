package com.example.pacelable

import android.content.Intent
import android.os.SystemClock
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.UserParcelable
import com.example.model.UserSerializable

@Composable
fun ParcelableComparisonScreen() {
    val context = LocalContext.current
    var comparisonResult by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val sampleUserParcelable =
        remember {
            UserParcelable(
                id = 1,
                name = "홍길동",
                email = "hong@example.com",
                age = 30,
                address = "서울시 강남구",
            )
        }

    val sampleUserSerializable =
        remember {
            UserSerializable(
                id = 1,
                name = "홍길동",
                email = "hong@example.com",
                age = 30,
                address = "서울시 강남구",
            )
        }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // 제목
        Text(
            text = "Parcelable vs Serializable",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
        )

        Text(
            text = "Android에서 객체를 전달하는 두 가지 방법 비교",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 사용자 정보 카드
        UserInfoCard(user = sampleUserParcelable)

        Spacer(modifier = Modifier.height(8.dp))

        // 개별 테스트 섹션
        Text(
            text = "개별 테스트",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )

        Button(
            onClick = {
                val intent =
                    Intent(context, ResultActivity::class.java).apply {
                        putExtra("user", sampleUserParcelable)
                        putExtra("type", "Parcelable")
                    }
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth(),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Text("Parcelable 테스트")
        }

        Button(
            onClick = {
                val intent =
                    Intent(context, ResultActivity::class.java).apply {
                        putExtra("user", sampleUserSerializable)
                        putExtra("type", "Serializable")
                    }
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth(),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                ),
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Text("Serializable 테스트")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 성능 비교 섹션
        Text(
            text = "성능 비교",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )

        FilledTonalButton(
            onClick = {
                isLoading = true
                val result =
                    comparePerformance(
                        sampleUserParcelable,
                        sampleUserSerializable,
                    )
                comparisonResult = result
                isLoading = false
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading,
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("측정 중...")
            } else {
                Text("성능 비교 실행")
            }
        }

        // 비교 결과
        if (comparisonResult.isNotEmpty()) {
            ComparisonResultCard(result = comparisonResult)
        }
    }
}

@Composable
fun UserInfoCard(user: UserParcelable) {
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
                text = "테스트 데이터",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            InfoRow(label = "이름", value = user.name)
            InfoRow(label = "이메일", value = user.email)
            InfoRow(label = "나이", value = "${user.age}세")
            InfoRow(label = "주소", value = user.address)
        }
    }
}

@Composable
fun InfoRow(
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
fun ComparisonResultCard(result: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = result,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
            )
        }
    }
}

fun comparePerformance(
    userParcelable: UserParcelable,
    userSerializable: UserSerializable,
): String {
    val iterations = 1000

    // Parcelable 테스트
    val parcelableStartTime = SystemClock.elapsedRealtime()
    repeat(iterations) {
        Intent().apply {
            putExtra("user", userParcelable)
        }
    }
    val parcelableEndTime = SystemClock.elapsedRealtime()
    val parcelableTime = parcelableEndTime - parcelableStartTime

    // Serializable 테스트
    val serializableStartTime = SystemClock.elapsedRealtime()
    repeat(iterations) {
        Intent().apply {
            putExtra("user", userSerializable)
        }
    }
    val serializableEndTime = SystemClock.elapsedRealtime()
    val serializableTime = serializableEndTime - serializableStartTime

    val speedDifference =
        if (parcelableTime > 0) {
            ((serializableTime.toFloat() / parcelableTime.toFloat()) * 100).toInt()
        } else {
            0
        }

    return """
📊 성능 비교 결과 (${iterations}회 반복)

✅ Parcelable: ${parcelableTime}ms
⚠️ Serializable: ${serializableTime}ms

🚀 Parcelable이 약 ${speedDifference - 100}% 더 빠릅니다!

💡 권장사항:
• Android 앱 내부 통신: Parcelable 사용
• 네트워크/파일 저장: Serializable 고려
• @Parcelize로 간편하게 구현 가능
        """.trimIndent()
}
