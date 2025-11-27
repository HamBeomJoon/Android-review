plugins {
    `kotlin-dsl`
}

group = "com.multi.module.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "practice.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidFeature") {
            id = "practice.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidHilt") {
            id = "practice.android.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        register("androidLibrary") {
            id = "practice.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}
