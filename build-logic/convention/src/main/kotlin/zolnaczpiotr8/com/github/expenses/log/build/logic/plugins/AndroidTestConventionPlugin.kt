package zolnaczpiotr8.com.github.expenses.log.build.logic.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.configurations.configureAndroid
import zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.configurations.configureKotlinJvm

class AndroidTestConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.test")
                apply("org.jetbrains.kotlin.android")
            }
            configureAndroid()
            configureKotlinJvm()
        }
    }
}
