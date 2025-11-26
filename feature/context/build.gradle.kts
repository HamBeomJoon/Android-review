plugins {
    id("practice.android.feature")
}

android {
    namespace = "com.example.context"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
}
