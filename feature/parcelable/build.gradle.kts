plugins {
    id("practice.android.feature")
}

android {
    namespace = "com.example.parcelable"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
    implementation(project(":core:model"))
}
