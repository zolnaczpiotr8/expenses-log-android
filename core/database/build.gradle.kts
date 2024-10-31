plugins {
    alias(libs.plugins.expenses.log.android.library)
    alias(libs.plugins.expenses.log.android.hilt)
    alias(libs.plugins.expenses.log.android.room)
}

dependencies {
    api(projects.core.model)
}
