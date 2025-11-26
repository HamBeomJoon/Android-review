package com.example.context

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.edit

@Composable
fun StorageScreen() {
    val context = LocalContext.current
    val sharedPrefs = context.getSharedPreferences("context_prefs", Context.MODE_PRIVATE)

    var inputText by remember { mutableStateOf("") }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Storage 실습", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("저장할 텍스트 입력") },
            modifier = Modifier.fillMaxWidth(),
        )

        Button(
            onClick = {
                sharedPrefs.edit { putString("name", inputText) }
                Toast.makeText(context, "SharedPreferences 저장 완료", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("SharedPreferences 저장")
        }

        Button(
            onClick = {
                val value = sharedPrefs.getString("name", "없음")
                Toast.makeText(context, "SharedPreferences 값: $value", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("SharedPreferences 읽기")
        }
    }
}
