package zolnaczpiotr8.com.github.expenses.log.build.logic.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import zolnaczpiotr8.com.github.expenses.log.build.logic.implementation
import zolnaczpiotr8.com.github.expenses.log.build.logic.libs
import zolnaczpiotr8.com.github.expenses.log.build.logic.plugins

class AndroidNavigationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(plugins("kotlin.serialization"))
            }

            dependencies {
                implementation(libs("kotlinx.serialization"))
                implementation(libs("androidx.navigation.compose"))
            }
        }
    }
}
