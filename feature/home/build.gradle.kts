plugins {
    alias(libs.plugins.expenses.log.feature)
}

dependencies {
    implementation(projects.core.data)
    implementation(libs.kotlinx.date.time)
}
