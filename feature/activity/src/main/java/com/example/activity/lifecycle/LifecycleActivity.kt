package com.example.activity.lifecycle

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.ReviewTheme
import com.example.ui.InfoCard
import com.example.ui.ScreenScaffold

class LifecycleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate 호출")
        setContent {
            ReviewTheme {
                LifecycleScreen(
                    onBackClick = { finish() },
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart 호출")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume 호출")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause 호출")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop 호출")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart 호출")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy 호출")
    }

    companion object {
        private const val TAG = "LifecycleActivity"
    }
}

@Composable
fun LifecycleScreen(onBackClick: () -> Unit) {
    val context = LocalContext.current

    ScreenScaffold(
        title = "Lifecycle 학습",
        onBackClick = onBackClick,
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
            InfoCard(
                title = "Activity Lifecycle",
                content =
                    """
                    onCreate → onStart → onResume
                    (Activity 실행 중)
                    onPause → onStop → onDestroy
                    
                    백그라운드에서 복귀:
                    onRestart → onStart → onResume
                    """.trimIndent(),
            )

            Button(
                onClick = {
                    val intent = Intent(context, LifecycleSecondActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("다른 Activity로 이동")
            }
        }
    }
}
