package com.example.activity.launchmode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*

class SingleTopActivity : ComponentActivity() {
    private val instanceId = ++instanceCount

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "✅ onCreate - Instance #$instanceId")

        setContent {
            MaterialTheme {
                ModeTestScreen(
                    title = "SingleTop Mode",
                    instanceId = instanceId,
                    description = "Top에 있으면 onNewIntent 호출, 없으면 새로 생성",
                    onSameActivityClick = {
                        val intent = Intent(this, SingleTopActivity::class.java)
                        startActivity(intent)
                    },
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d(TAG, "🔄 onNewIntent - Instance #$instanceId (재사용됨! onCreate 안 호출됨)")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "❌ onDestroy - Instance #$instanceId")
    }

    companion object {
        private val TAG = "SingleTopActivity"

        private var instanceCount = 0
    }
}
