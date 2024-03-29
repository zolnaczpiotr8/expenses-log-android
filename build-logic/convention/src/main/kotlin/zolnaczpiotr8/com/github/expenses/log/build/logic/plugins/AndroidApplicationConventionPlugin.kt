package zolnaczpiotr8.com.github.expenses.log.build.logic.plugins

import com.dropbox.gradle.plugins.dependencyguard.DependencyGuardPluginExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.configurations.configureAndroid
import zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.configurations.configureCompose
import zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.configurations.configureKotlinJvm

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("expenses.log.android.hilt")
                apply("com.dropbox.dependency-guard")
            }
            configureAndroid()
            configureKotlinJvm()
            configureCompose()
            configureDependencyGuard()
        }
    }

    private fun Project.configureDependencyGuard() {
        dependencyGuard {
            configuration("releaseRuntimeClasspath")
        }
    }

    private fun Project.dependencyGuard(configuration: DependencyGuardPluginExtension.() -> Unit) {
        extensions.findByType<DependencyGuardPluginExtension>()?.let {
            configuration.invoke(it)
        }
    }
}
