plugins {
    alias(libs.plugins.expenses.log.ksp.library)
    alias(libs.plugins.expenses.log.hilt)
}

dependencies {
    api(projects.core.model)
    api(projects.core.datastore.proto)
    api(libs.androidx.datastore)
}
