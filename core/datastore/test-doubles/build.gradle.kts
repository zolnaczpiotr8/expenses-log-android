plugins {
    alias(libs.plugins.expenses.log.ksp.library)
    alias(libs.plugins.expenses.log.hilt)
}

dependencies {
    compileOnly(libs.hilt.android.testing)
    compileOnly(projects.core.datastore)
    compileOnly(projects.core.datastore.proto)
    compileOnly(libs.androidx.datastore)
}
