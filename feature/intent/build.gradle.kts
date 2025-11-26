plugins {
    id("practice.android.feature")
}

android {
    namespace = "com.example.intent"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
}
