package com.example.activity.launchmode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.designsystem.theme.ReviewTheme

class StandardActivity : ComponentActivity() {
    private val instanceId = ++instanceCount

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "✅ onCreate - Instance #$instanceId (항상 새로 생성됨)")

        setContent {
            ReviewTheme {
                ModeTestScreen(
                    title = "Standard Mode",
                    instanceId = instanceId,
                    description = "버튼을 누를 때마다 새 인스턴스가 생성됩니다",
                    onSameActivityClick = {
                        val intent = Intent(this, StandardActivity::class.java)
                        startActivity(intent)
                    },
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "❌ onDestroy - Instance #$instanceId")
    }

    companion object {
        private const val TAG = "StandardActivity"

        private var instanceCount = 0
    }
}
