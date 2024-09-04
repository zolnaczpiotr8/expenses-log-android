package zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.configurations

import org.gradle.api.Project
import org.gradle.api.file.Directory
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import zolnaczpiotr8.com.github.expenses.log.build.logic.implementation
import zolnaczpiotr8.com.github.expenses.log.build.logic.libs
import java.nio.file.Paths

internal fun Project.configureCompose() {
    commonAndroid {
        buildFeatures {
            compose = true
        }
    }

    composeCompiler {
        enableStrongSkippingMode.set(true)

        if (enableComposeCompilerMetrics()) {
            metricsDestination.set(composeCompilerDir("metrics"))
        }

        if (enableComposeCompilerReports()) {
            reportsDestination.set(composeCompilerDir("reports"))
        }
    }

    dependencies {
        implementation(platform(libs("androidx.compose.bom")))
        implementation(libs("androidx.compose.ui.tooling"))
        implementation(libs("androidx.compose.ui.tooling.preview"))
    }
}

internal fun Project.composeCompiler(configuration: ComposeCompilerGradlePluginExtension.() -> Unit) {
    extensions.findByType<ComposeCompilerGradlePluginExtension>()?.let {
        configuration.invoke(it)
    }
}

private fun Project.enableComposeCompilerMetrics(): Boolean =
    getGradleProperty("composeCompiler.enableMetrics").toBoolean()

private fun Project.enableComposeCompilerReports(): Boolean =
    getGradleProperty("composeCompiler.enableReports").toBoolean()

private fun Project.getGradleProperty(
    key: String,
): String? =
    with(project.providers.gradleProperty(key)) {
        if (isPresent) {
            get()
        } else {
            null
        }
    }

private fun Project.composeCompilerDir(
    dir: String,
): Provider<Directory> =
    layout.buildDirectory.dir(Paths.get("compose", dir).toString())
