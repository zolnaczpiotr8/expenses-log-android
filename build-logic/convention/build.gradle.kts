import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "zolnaczpiotr8.com.github.expenses.log.build.logic"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
        vendor = JvmVendorSpec.ORACLE
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.addAll(
        listOf(
            "-Xlint:all",
        ),
    )
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        allWarningsAsErrors.set(true)
    }
}

gradlePlugin {
    plugins {
        register("android-application") {
            id = "expenses.log.android.application"
            implementationClass =
                "zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.AndroidApplicationConventionPlugin"
        }
        register("android-feature") {
            id = "expenses.log.android.feature"
            implementationClass =
                "zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.AndroidFeatureConventionPlugin"
        }
        register("android-hilt") {
            id = "expenses.log.android.hilt"
            implementationClass =
                "zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.AndroidHiltConventionPlugin"
        }
        register("android-library") {
            id = "expenses.log.android.library"
            implementationClass =
                "zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.AndroidLibraryConventionPlugin"
        }
        register("android-test") {
            id = "expenses.log.android.test"
            implementationClass =
                "zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.AndroidTestConventionPlugin"
        }
        register("jvm-library") {
            id = "expenses.log.jvm.library"
            implementationClass =
                "zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.JvmLibraryConventionPlugin"
        }
        register("android-room") {
            id = "expenses.log.android.room"
            implementationClass =
                "zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.AndroidRoomConventionPlugin"
        }
        register("android-compose") {
            id = "expenses.log.android.compose"
            implementationClass =
                "zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.AndroidComposeConventionPlugin"
        }
        register("android-navigation") {
            id = "expenses.log.android.navigation"
            implementationClass =
                "zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.AndroidNavigationConventionPlugin"
        }
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.kotlin.compose)
    compileOnly(libs.room)
    compileOnly(libs.hilt.android.gradle)
}
