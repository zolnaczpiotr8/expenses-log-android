plugins {
    alias(libs.plugins.expenses.log.feature)
}

dependencies {
    implementation(projects.core.data)
}
