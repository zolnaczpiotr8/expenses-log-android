plugins {
    `kotlin-dsl`
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

dependencies {
    implementation(libs.android.gradle)
    implementation(libs.kotlin.gradle)
    implementation(libs.kotlin.jvm)
    implementation(libs.kotlin.compose)
    implementation(libs.hilt.android.gradle)
    implementation(libs.ksp)
    implementation(libs.kotlin.serialization)
}
