package zolnaczpiotr8.com.github.expenses.log.build.logic.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import zolnaczpiotr8.com.github.expenses.log.build.logic.implementation
import zolnaczpiotr8.com.github.expenses.log.build.logic.libs

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("expenses.log.compose.library")
                apply("expenses.log.android.hilt")
            }

            dependencies {
                implementation(libs("androidx.lifecycle.viewmodel.compose"))
                implementation(project(":core:ui"))
            }
        }
    }
}
