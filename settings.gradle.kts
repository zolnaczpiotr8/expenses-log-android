pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Expenses-Log"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(
    ":app",
    ":benchmark",
    ":core:ui",
    ":core:model",
    ":core:data",
    ":core:database",
    ":core:datastore",
    ":core:testing",
    ":core:testing-hilt",
    ":feature:home",
)
