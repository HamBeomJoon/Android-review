plugins {
    id("practice.android.feature")
}

android {
    namespace = "com.example.ui"
}

dependencies {
    implementation(project(":core:designsystem"))

    implementation("io.coil-kt:coil-compose:2.5.0")
}
