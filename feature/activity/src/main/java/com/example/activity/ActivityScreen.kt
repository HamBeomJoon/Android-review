package com.example.activity

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.activity.launchmode.LaunchModeActivity
import com.example.activity.lifecycle.LifecycleActivity

@Composable
fun ActivityScreen() {
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
            text = "Activity 학습 메뉴",
            style = MaterialTheme.typography.headlineLarge,
        )

        HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

        // 1. Lifecycle 학습
        Card(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val intent = Intent(context, LifecycleActivity::class.java)
                context.startActivity(intent)
            },
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "1️⃣ Activity Lifecycle",
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "onCreate, onStart, onResume 등 생명주기 학습",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }

        // 2. LaunchMode 학습
        Card(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val intent = Intent(context, LaunchModeActivity::class.java)
                context.startActivity(intent)
            },
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "2️⃣ Launch Mode",
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "standard, singleTop, singleTask 등",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }

        // 3. Task & Back Stack
        Card(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                // TODO: TaskActivity 만들기
            },
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "3️⃣ Task & Back Stack",
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "Activity 스택 관리",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}
