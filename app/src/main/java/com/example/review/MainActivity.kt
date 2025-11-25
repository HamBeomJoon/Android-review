package com.example.review

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.activity.ActivityScreen
import com.example.intent.IntentScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable("home") {
            HomeScreen(
                onActivityClick = { navController.navigate("activity") },
                onIntentClick = { navController.navigate("intent") },
            )
        }

        composable("activity") {
            ActivityScreen() // feature:activity 모듈
        }

        composable("intent") {
            IntentScreen() // feature:intent 모듈
        }
    }
}

@Composable
fun HomeScreen(
    onActivityClick: () -> Unit,
    onIntentClick: () -> Unit,
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
    }
}
