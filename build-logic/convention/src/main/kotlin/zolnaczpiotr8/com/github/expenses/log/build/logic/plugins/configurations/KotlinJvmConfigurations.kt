package zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.configurations

import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.jvm.toolchain.JvmVendorSpec
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinJvm() {
    configureJava()
    configureKotlin()
}

private fun Project.configureJava() {
    configureToolchain()
    configureWarnings()
}

private fun Project.configureToolchain() {
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
            vendor.set(JvmVendorSpec.ORACLE)
        }
    }
}

private fun Project.java(configuration: JavaPluginExtension.() -> Unit) {
    extensions.findByType<JavaPluginExtension>()?.let {
        configuration.invoke(it)
    }
}

private fun Project.configureWarnings() {
    tasks.withType<JavaCompile>().configureEach {
        options.compilerArgs.addAll(
            listOf(
                "-Xlint:all",
            ),
        )
    }
}

private fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            allWarningsAsErrors.set(true)
        }
    }
}
