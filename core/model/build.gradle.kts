plugins {
    id("practice.android.library")
    id("org.jetbrains.kotlin.plugin.parcelize")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.model"
}
dependencies {
    implementation(libs.kotlinx.serialization.json)
}
