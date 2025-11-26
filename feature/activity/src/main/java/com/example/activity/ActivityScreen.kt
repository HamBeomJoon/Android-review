package com.example.activity

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.activity.launchmode.LaunchModeActivity
import com.example.activity.lifecycle.LifecycleActivity
import com.example.ui.MenuCard
import com.example.ui.ScreenHeader

@Composable
fun ActivityScreen() {
    val context = LocalContext.current

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        ScreenHeader(title = "Activity 학습")

        MenuCard(
            title = "1️⃣ Activity Lifecycle",
            description = "onCreate, onStart, onResume 등 생명주기 학습",
            onClick = {
                val intent = Intent(context, LifecycleActivity::class.java)
                context.startActivity(intent)
            },
        )

        MenuCard(
            title = "2️⃣ Launch Mode",
            description = "standard, singleTop, singleTask 등",
            onClick = {
                val intent = Intent(context, LaunchModeActivity::class.java)
                context.startActivity(intent)
            },
        )

        MenuCard(
            title = "3️⃣ Task & Back Stack",
            description = "Activity 스택 관리",
            onClick = {
                // TODO: TaskActivity 만들기
            },
        )
    }
}
