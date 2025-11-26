package com.example.context.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.context.ContextHomeScreen
import com.example.context.ResourceScreen
import com.example.context.StorageScreen
import com.example.context.SystemServiceScreen

fun NavGraphBuilder.contextGraph(navController: NavHostController) {
    navigation(
        startDestination = "context/home",
        route = "context_graph",
    ) {
        // 진입점
        composable("context/home") {
            ContextHomeScreen(navController)
        }

        // 1. SystemService 실습
        composable("context/system_service") {
            SystemServiceScreen()
        }

        // 2. Resource 접근 실습
        composable("context/resource_access") {
            ResourceScreen()
        }

        // 3. Storage(DataStore/SharedPreferences) 실습
        composable("context/storage") {
            StorageScreen()
        }
    }
}
