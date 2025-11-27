plugins {
    id("practice.android.feature")
}

android {
    namespace = "com.example.dialog"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
    implementation(libs.androidx.media3.exoplayer)
}
