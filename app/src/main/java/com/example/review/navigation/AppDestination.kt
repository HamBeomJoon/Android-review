package com.example.review.navigation

sealed class AppDestination(
    val route: String,
) {
    data object Home : AppDestination("home")

    data object ActivityLearning : AppDestination("activity")

    data object IntentLearning : AppDestination("intent")

    data object ContextLearning : AppDestination("context/home")
}
