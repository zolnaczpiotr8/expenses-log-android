package zolnaczpiotr8.com.github.expenses.log.build.logic.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.configurations.configureAndroid
import zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.configurations.configureKotlinJvm

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("expenses.log.android.hilt")
                apply("expenses.log.android.navigation")
                apply("expenses.log.android.compose")
            }
            configureAndroid()
            configureKotlinJvm()
        }
    }
}
