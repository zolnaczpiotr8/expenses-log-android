package zolnaczpiotr8.com.github.expenses.log.build.logic.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import zolnaczpiotr8.com.github.expenses.log.build.logic.androidTestImplementation
import zolnaczpiotr8.com.github.expenses.log.build.logic.androidTestUtil
import zolnaczpiotr8.com.github.expenses.log.build.logic.debugImplementation
import zolnaczpiotr8.com.github.expenses.log.build.logic.implementation
import zolnaczpiotr8.com.github.expenses.log.build.logic.libs
import zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.configurations.configureCompose

class AndroidComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            configureCompose()

            dependencies {
                implementation(platform(libs("androidx.compose.bom")))
                implementation(libs("androidx.compose.ui.tooling"))
                implementation(libs("androidx.compose.ui.tooling.preview"))
                implementation(libs("androidx.compose.material3"))
                implementation(libs("androidx.compose.material.icons.extended"))
                androidTestImplementation(platform(libs("androidx.compose.bom")))
                androidTestImplementation(libs("androidx.compose.ui.test.junit4"))
                androidTestImplementation(libs("androidx.test.runner"))
                androidTestImplementation(libs("androidx.test.espresso.device"))
                debugImplementation(libs("androidx.compose.ui.test.manifest"))
                androidTestUtil(libs("androidx.test.orchestrator"))
                androidTestImplementation(project(":core:testing"))
            }
        }
    }
}
