package com.example.activity.launchmode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class LaunchModeActivity : ComponentActivity() {
    private var instanceId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instanceId = ++instanceCount
        Log.d(TAG, "🔷 onCreate - Instance #$instanceId")

        setContent {
            MaterialTheme {
                LaunchModeScreen(instanceId = instanceId)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d(TAG, "🔄 onNewIntent - Instance #$instanceId (재사용됨)")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "🔻 onDestroy - Instance #$instanceId")
    }

    companion object {
        private const val TAG = "LaunchModeActivity"
        private var instanceCount = 0
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchModeScreen(instanceId: Int) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LaunchMode 학습") },
            )
        },
    ) { padding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // 현재 인스턴스 표시
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors =
                    CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    Text(
                        text = "현재 인스턴스",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = "# $instanceId",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            // Standard Mode 설명
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
                        text = "📘 standard (기본)",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = "항상 새 인스턴스 생성\nA → A → A (3개 쌓임)",
                        style = MaterialTheme.typography.bodySmall,
                    )

                    Button(
                        onClick = {
                            val intent = Intent(context, StandardActivity::class.java)
                            context.startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("Standard Activity 열기")
                    }
                }
            }

            // SingleTop Mode 설명
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
                        text = "📗 singleTop",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = "Top에 있으면 재사용 (onNewIntent)\nA(top) → A = A (재사용)\nA → B → A = A-B-A (새로 생성)",
                        style = MaterialTheme.typography.bodySmall,
                    )

                    Button(
                        onClick = {
                            val intent = Intent(context, SingleTopActivity::class.java)
                            context.startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("SingleTop Activity 열기")
                    }
                }
            }

            // SingleTask Mode 설명
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors =
                    CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                    ),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = "📙 singleTask",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = "Task에 하나만 존재\nA-B-C → A = A (B,C 제거됨)",
                        style = MaterialTheme.typography.bodySmall,
                    )

                    Button(
                        onClick = {
                            val intent = Intent(context, SingleTaskActivity::class.java)
                            context.startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("SingleTask Activity 열기")
                    }
                }
            }
        }
    }
}
