package com.example.context

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
fun ResourceScreen() {
    val context = LocalContext.current
    val resources = context.resources
    val appName = context.getString(R.string.app_name)

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Resource 접근 실습", style = MaterialTheme.typography.headlineSmall)

        Text("앱 이름: $appName")

        Button(onClick = {
            val metrics = resources.displayMetrics
            Toast
                .makeText(
                    context,
                    "화면 width: ${metrics.widthPixels}, height: ${metrics.heightPixels}",
                    Toast.LENGTH_SHORT,
                ).show()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("DisplayMetrics 확인")
        }
    }
}
