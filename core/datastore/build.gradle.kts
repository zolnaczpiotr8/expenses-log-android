plugins {
    alias(libs.plugins.expenses.log.ksp.library)
    alias(libs.plugins.expenses.log.hilt)
}

dependencies {
    api(projects.core.model)
    implementation(projects.core.datastore.proto)
    implementation(libs.androidx.datastore)
}
