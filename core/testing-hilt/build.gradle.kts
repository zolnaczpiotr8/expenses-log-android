plugins {
    alias(libs.plugins.expenses.log.android.library)
}

dependencies {
    implementation(libs.androidx.test.runner)
    implementation(libs.hilt.android.testing)
}
