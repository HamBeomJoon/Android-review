package com.example.review.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.activity.ActivityScreen
import com.example.context.navigation.contextGraph
import com.example.dialog.DialogScreen
import com.example.intent.IntentScreen
import com.example.pacelable.ParcelableComparisonScreen
import com.example.review.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestination.Home.route,
    ) {
        composable(AppDestination.Home.route) {
            HomeScreen(
                onActivityClick = { navController.navigate(AppDestination.ActivityLearning.route) },
                onIntentClick = { navController.navigate(AppDestination.IntentLearning.route) },
                onContextClick = { navController.navigate(AppDestination.ContextLearning.route) },
                onParcelableClick = { navController.navigate(AppDestination.ParcelableComparison.route) },
                onDialogClick = { navController.navigate(AppDestination.DialogLearning.route) },
            )
        }

        composable(AppDestination.ActivityLearning.route) {
            ActivityScreen() // feature:activity 모듈
        }

        composable(AppDestination.IntentLearning.route) {
            IntentScreen() // feature:intent 모듈
        }

        contextGraph(navController) // feature:context 모듈

        composable(AppDestination.ParcelableComparison.route) {
            ParcelableComparisonScreen() // feature:parcelable 모듈
        }

        composable(AppDestination.DialogLearning.route) {
            DialogScreen() // feature:dialog 모듈
        }
    }
}
