package com.example.activity.launchmode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.designsystem.theme.ReviewTheme

class SingleTaskActivity : ComponentActivity() {
    private val instanceId = ++instanceCount

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate - Instance #$instanceId")

        setContent {
            ReviewTheme {
                ModeTestScreen(
                    title = "SingleTask Mode",
                    instanceId = instanceId,
                    description = "위에 있는 Activity들을 모두 제거하고 재사용",
                    onSameActivityClick = {
                        val intent = Intent(this, SingleTaskActivity::class.java)
                        startActivity(intent)
                    },
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent - Instance #$instanceId (위의 Activity들 제거됨)")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy - Instance #$instanceId")
    }

    companion object {
        private val TAG = "SingleTaskActivity"

        private var instanceCount = 0
    }
}
