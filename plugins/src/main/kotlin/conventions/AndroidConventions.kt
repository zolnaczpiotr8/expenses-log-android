package conventions

import application.id.APPLICATION_ID
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.TestExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import dependencies.androidTestImplementation
import dependencies.androidTestUtil
import dependencies.libs
import dependencies.targetSdk
import dependencies.versions
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import java.io.File
import java.util.Locale

fun Project.configureAndroid() {
    configureResources()
    configureInstrumentationTests()
    configureLint()
    configureNamespace()
    configureNdk()
    configureCompileSdk()
    configureMinSdk()
    configureTargetSdk()
}

private fun Project.configureResources() {
    androidApplication {
        androidResources {
            localeFilters.addAll(listOf("en", "pl"))
        }
    }
}

private fun Project.configureInstrumentationTests() {
    disableInstrumentationTests()
    if (androidTestDirExists()) {
        enableInstrumentationTests()
        configureTestRunner()
        enableTestOrchestrator()
        addInstrumentationTestsDependencies()
    }
}

private fun Project.disableInstrumentationTests() = enableInstrumentationTests(false)

private fun Project.androidTestDirExists(): Boolean = projectDir
    .resolve("src")
    .resolve("androidTest")
    .exists()

private fun Project.enableInstrumentationTests() = enableInstrumentationTests(true)

private fun Project.enableInstrumentationTests(
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

private fun Project.addInstrumentationTestsDependencies() {
    dependencies {
        androidTestImplementation(libs("androidx.test.runner"))
        androidTestUtil(libs("androidx.test.orchestrator"))
    }
}

private fun Project.configureTestRunner() {
    commonAndroid {
        defaultConfig {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            testInstrumentationRunnerArguments["clearPackageData"] = "true"
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
        }.toJavaPackage()
    }
}

private fun String.toJavaPackage(): String {
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

private fun Project.configureTargetSdk() {
    androidApplication {
        defaultConfig.targetSdk = targetSdk()
    }

    androidTest {
        defaultConfig.targetSdk = targetSdk()
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
