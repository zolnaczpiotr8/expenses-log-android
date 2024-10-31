package zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.configurations

import org.gradle.api.Project

internal fun Project.configureHilt() {
    commonAndroid {
        defaultConfig {
            testInstrumentationRunner = testInstrumentationRunner
                ?: "zolnaczpiotr8.com.github.expenses.log.core.testing.AndroidHiltRunner"
            testInstrumentationRunnerArguments["clearPackageData"] = "true"
        }
    }
}
