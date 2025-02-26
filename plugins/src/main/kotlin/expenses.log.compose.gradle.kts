import conventions.commonAndroid
import dependencies.androidTestImplementation
import dependencies.debugImplementation
import dependencies.implementation
import dependencies.libs
import org.gradle.api.file.RegularFile
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import java.nio.file.Paths

plugins {
    id("org.jetbrains.kotlin.plugin.compose")
}

commonAndroid {
    buildFeatures {
        compose = true
    }
}

composeCompiler {
    stabilityConfigurationFiles.addAll(composeConfigs())
    metricsDestination.set(composeCompilerDir("metrics"))
    reportsDestination.set(composeCompilerDir("reports"))
}

private fun composeConfigs(): List<RegularFile> =
    listOf(
        rootProject,
        project,
    ).map(Project::getLayout)
        .map(ProjectLayout::getProjectDirectory)
        .map {
            it.file("compose.conf")
        }.filter {
            it.asFile.exists()
        }

private fun Project.composeCompiler(configuration: ComposeCompilerGradlePluginExtension.() -> Unit) {
    extensions.findByType<ComposeCompilerGradlePluginExtension>()?.let {
        configuration.invoke(it)
    }
}

private fun Project.composeCompilerDir(dir: String): Provider<Directory> = layout.buildDirectory.dir(Paths.get("compose", dir).toString())

dependencies {
    implementation(platform(libs("androidx.compose.bom")))
    implementation(libs("androidx.compose.material3"))
    implementation(libs("androidx.compose.material.icons.extended"))
    androidTestImplementation(platform(libs("androidx.compose.bom")))
    androidTestImplementation(libs("androidx.compose.ui.test.junit4"))
    debugImplementation(libs("androidx.compose.ui.test.manifest"))
}
