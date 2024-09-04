plugins {
    alias(libs.plugins.expenses.log.android.library)
    alias(libs.plugins.expenses.log.android.hilt)
}

dependencies {
    api(libs.androidx.datastore)
    api(projects.core.model)
}
