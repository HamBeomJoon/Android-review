plugins {
    id("practice.android.application")
    id("practice.android.hilt")
}

android {
    namespace = "com.example.review"

    defaultConfig {
        applicationId = "com.example.review"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":feature:activity"))
    implementation(project(":feature:intent"))
    implementation(project(":feature:context"))
}
