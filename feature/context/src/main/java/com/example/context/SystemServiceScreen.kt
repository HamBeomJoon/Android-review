package com.example.context

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SystemServiceScreen() {
    val context = LocalContext.current
    // API 31 이상이면 VibratorManager, 하위이면 Vibrator 사용
    val vibratorManager: VibratorManager? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
        } else {
            null
        }

    val vibrator: Vibrator? =
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> vibratorManager?.defaultVibrator
            else -> context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
        }
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("System Service 실습", style = MaterialTheme.typography.headlineSmall)

        Button(onClick = {
            vibrator?.vibrate(
                VibrationEffect.createOneShot(
                    200,
                    VibrationEffect.DEFAULT_AMPLITUDE,
                ),
            )
        }, modifier = Modifier.fillMaxWidth()) {
            Text("진동 테스트")
        }

        Button(onClick = {
            val clip = ClipData.newPlainText("label", "Context SystemService Test")
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "클립보드에 텍스트 저장됨", Toast.LENGTH_SHORT).show()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Clipboard 테스트")
        }
    }
}
