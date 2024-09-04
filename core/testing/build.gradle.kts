plugins {
    alias(libs.plugins.expenses.log.android.library)
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui.test.junit4)
}
