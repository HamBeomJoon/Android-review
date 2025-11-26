package com.example.intent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
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
import com.example.ui.DataDisplayCard
import com.example.ui.ScreenScaffold

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Intent에서 데이터 받기
        val message = intent.getStringExtra("message") ?: "데이터 없음"
        val number = intent.getIntExtra("number", 0)

        setContent {
            ReviewTheme {
                SecondScreen(
                    message = message,
                    number = number,
                    onBackClick = { finish() },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(
    message: String,
    number: Int,
    onBackClick: () -> Unit,
) {
    ScreenScaffold(
        title = "Second Activity",
        onBackClick = onBackClick,
    ) { padding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            DataDisplayCard(
                title = "전달받은 데이터",
                data =
                    mapOf(
                        "메시지" to message,
                        "숫자" to number.toString(),
                    ),
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("뒤로 가기")
            }
        }
    }
}
