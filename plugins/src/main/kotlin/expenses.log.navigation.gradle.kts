import dependencies.implementation
import dependencies.libs
import org.gradle.kotlin.dsl.dependencies

plugins {
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
    implementation(libs("kotlinx.serialization"))
    implementation(libs("androidx.navigation.compose"))
}
