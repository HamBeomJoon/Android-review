package com.example.activity.lifecycle

import android.os.Bundle
import android.util.Log
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class LifecycleSecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "📗 onCreate 호출")

        setContent {
            MaterialTheme {
                SecondLifecycleScreen(
                    onBackClick = { finish() },
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "📘 onStart 호출")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "📙 onResume 호출")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "📕 onPause 호출")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "📔 onStop 호출")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "📓 onDestroy 호출")
    }

    companion object {
        private const val TAG = "SecondActivity"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondLifecycleScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Second Activity") },
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
        ) {
            Text(
                text = "이동했을 때:\nMainActivity: onPause → onStop\nSecondActivity: onCreate → onStart → onResume",
                style = MaterialTheme.typography.bodyMedium,
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("뒤로 가기")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "뒤로 갈 때:\nSecondActivity: onPause → onStop → onDestroy\nMainActivity: onRestart → onStart → onResume",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
