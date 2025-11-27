pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "review"
include(":app")
include(":feature:activity")
include(":feature:intent")
include(":core:common")
include(":core:ui")
include(":core:network")
include(":core:data")
include(":core:designsystem")
include(":feature:context")
include(":core:model")
include(":feature:parcelable")
include(":feature:dialog")
