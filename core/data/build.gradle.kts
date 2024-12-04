plugins {
    alias(libs.plugins.expenses.log.ksp.library)
    alias(libs.plugins.expenses.log.hilt)
}

dependencies {
    api(projects.core.model)
    api(libs.kotlinx.collections.immutable)
    implementation(projects.core.datastore)
    implementation(projects.core.database)
}
