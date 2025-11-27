package com.example.review.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onActivityClick: () -> Unit,
    onIntentClick: () -> Unit,
    onContextClick: () -> Unit,
    onParcelableClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            onClick = onActivityClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("activity 학습")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onIntentClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("intent 학습")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onContextClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("context 학습")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onParcelableClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("parcelable 학습")
        }
    }
}
