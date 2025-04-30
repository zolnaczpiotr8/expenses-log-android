rootProject.name = "Expenses-Log"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
includeBuild("plugins")
include(
    ":app",
    ":benchmark",
    ":core:ui",
    ":core:model",
    ":core:data",
    ":core:database",
    ":core:datastore",
    ":core:datastore:proto",
    ":core:datastore:test-doubles",
    ":feature:home",
    ":feature:expense",
    ":feature:category",
    ":feature:settings",
)
