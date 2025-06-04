plugins {
    alias(libs.plugins.expenses.log.android.library)
}

dependencies {
    api(libs.kotlinx.date.time)
    api(libs.kotlinx.collections.immutable)
}
