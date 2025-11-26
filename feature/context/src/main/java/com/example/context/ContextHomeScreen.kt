package com.example.context

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ContextHomeScreen(navController: NavController) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Context 학습", style = MaterialTheme.typography.headlineMedium)

        Button(
            onClick = { navController.navigate("context/system_service") },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("1. SystemService 실습")
        }

        Button(
            onClick = { navController.navigate("context/resource_access") },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("2. Resource 접근 실습")
        }

        Button(
            onClick = { navController.navigate("context/storage") },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("3. Storage(DataStore/SharedPreferences) 실습")
        }
    }
}
