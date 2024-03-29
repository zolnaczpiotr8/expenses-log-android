package zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.configurations

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.TestExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.Project
import zolnaczpiotr8.com.github.expenses.log.build.logic.versions
import java.io.File
import java.util.Locale

const val APPLICATION_ID = "zolnaczpiotr8.com.github.expenses.log"

internal fun Project.configureAndroid() {
    configureAndroidTest()
    configureLint()
    configureNamespace()
    configureNdk()
    configureCompileSdk()
    configureMinSdk()
    configureTargetSdk()
}

private fun Project.configureAndroidTest() {
    disableAndroidTest()
    if (androidTestDirExists()) {
        enableAndroidTest()
        configureTestInstrumentationRunner()
        configureManagedDevices()
        configureEmulatorControl()
        enableTestOrchestrator()
    }
}

private fun Project.androidTestDirExists(): Boolean =
    projectDir
        .resolve("src")
        .resolve("androidTest")
        .exists()

private fun Project.disableAndroidTest() =
    enableAndroidTest(false)

private fun Project.enableAndroidTest() =
    enableAndroidTest(true)

private fun Project.enableAndroidTest(
    enable: Boolean,
) {
    applicationComponents {
        beforeVariants {
            it.enableAndroidTest = enable
        }
    }
    libraryComponents {
        beforeVariants {
            it.enableAndroidTest = enable
        }
    }
}

private fun Project.applicationComponents(configuration: ApplicationAndroidComponentsExtension.() -> Unit) {
    extensions
        .findByType(ApplicationAndroidComponentsExtension::class.java)
        ?.let(configuration::invoke)
}

private fun Project.libraryComponents(configuration: LibraryAndroidComponentsExtension.() -> Unit) {
    extensions
        .findByType(LibraryAndroidComponentsExtension::class.java)
        ?.let(configuration::invoke)
}

private fun Project.configureTestInstrumentationRunner() {
    commonAndroid {
        defaultConfig {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            testInstrumentationRunnerArguments["clearPackageData"] = "true"
        }
    }
}

private fun Project.configureManagedDevices() {
    commonAndroid {
        testOptions {
            managedDevices {
                localDevices.create("compact") {
                    device = "Pixel 2"
                    apiLevel = 34
                    systemImageSource = "aosp-atd"
                }
            }
        }
    }
}

private fun Project.enableTestOrchestrator() {
    commonAndroid {
        testOptions {
            execution = "ANDROIDX_TEST_ORCHESTRATOR"
        }
    }
}

private fun Project.configureEmulatorControl() {
    commonAndroid {
        testOptions {
            emulatorControl {
                enable = true
            }
        }
    }
}

private fun Project.configureLint() {
    commonAndroid {
        lint {
            warningsAsErrors = true
            checkAllWarnings = true
            abortOnError = true
            ignoreWarnings = false
            ignoreTestSources = false
            checkTestSources = true
        }
    }
}

private fun Project.configureNamespace() {
    commonAndroid {
        namespace = buildString {
            append(APPLICATION_ID)
            append(project.path)
        }.toJavaPackageFormat()
    }
}

private fun String.toJavaPackageFormat(): String {
    val packageSeparator = "."
    return replace(
        File.pathSeparatorChar.toString(),
        packageSeparator,
    ).replace(
        "-",
        packageSeparator,
    ).lowercase(Locale.US)
}

private fun Project.configureNdk() {
    commonAndroid {
        ndkVersion = versions("ndk")
    }
}

private fun Project.configureCompileSdk() {
    commonAndroid {
        compileSdk = versions("compile.sdk").toInt()
    }
}

private fun Project.configureMinSdk() {
    commonAndroid {
        defaultConfig.minSdk = versions("min.sdk").toInt()
    }
}

internal fun Project.commonAndroid(configuration: CommonExtension<*, *, *, *, *, *>.() -> Unit) {
    extensions
        .findByType(CommonExtension::class.java)
        ?.let(configuration::invoke)
}

private fun Project.configureTargetSdk() {
    val targetSdk = versions("target.sdk").toInt()
    androidApplication {
        defaultConfig.targetSdk = targetSdk
    }

    androidTest {
        defaultConfig.targetSdk = targetSdk
    }
}

private fun Project.androidApplication(configuration: ApplicationExtension.() -> Unit) {
    extensions
        .findByType(ApplicationExtension::class.java)
        ?.let(configuration::invoke)
}

private fun Project.androidTest(configuration: TestExtension.() -> Unit) {
    extensions
        .findByType(TestExtension::class.java)
        ?.let(configuration::invoke)
}
