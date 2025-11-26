plugins {
    id("practice.android.feature")
}

android {
    namespace = "com.example.activity"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
}
