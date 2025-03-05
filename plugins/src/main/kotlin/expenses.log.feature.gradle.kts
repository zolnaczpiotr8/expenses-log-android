import dependencies.implementation
import dependencies.libs
import org.gradle.kotlin.dsl.dependencies

plugins {
    id("expenses.log.ksp.library")
    id("expenses.log.hilt")
    id("expenses.log.navigation")
    id("expenses.log.compose")
}

dependencies {
    implementation(libs("androidx.hilt.navigation.compose"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
}
