package zolnaczpiotr8.com.github.expenses.log.build.logic.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import zolnaczpiotr8.com.github.expenses.log.build.logic.androidTestImplementation
import zolnaczpiotr8.com.github.expenses.log.build.logic.implementation
import zolnaczpiotr8.com.github.expenses.log.build.logic.ksp
import zolnaczpiotr8.com.github.expenses.log.build.logic.libs
import zolnaczpiotr8.com.github.expenses.log.build.logic.plugins
import zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.configurations.configureHilt

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(plugins("ksp"))
                apply("com.google.dagger.hilt.android")
            }

            configureHilt()

            dependencies {
                implementation(libs("hilt.android"))
                ksp(libs("hilt.android.compiler"))
                androidTestImplementation(project(":core:testing-hilt"))
                androidTestImplementation(libs("hilt.android.testing"))
            }
        }
    }
}
