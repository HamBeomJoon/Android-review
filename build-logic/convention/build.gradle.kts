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
        register("androidFeature") {
            id = "practice.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}
