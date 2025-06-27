plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.androidx.baseline.profile)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.hilt.android)
}

composeCompiler {
    stabilityConfigurationFiles.add(
        layout.projectDirectory.file("compose.conf"),
    )
}

room {
    schemaDirectory("database-schema")
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.addAll(
        listOf(
            "-Xlint:all",
        ),
    )
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        optIn.addAll("kotlin.time.ExperimentalTime")
        allWarningsAsErrors.set(true)
    }
}

android {
    buildFeatures {
        compose = true
    }

    androidResources {
        localeFilters.addAll(listOf("en", "pl"))
    }

    compileSdk =
        libs.versions.compile.sdk
            .get()
            .toInt()
    namespace = "zolnaczpiotr8.com.github.expenses.log"

    defaultConfig {
        minSdk = 26
        targetSdk =
            libs.versions.compile.sdk
                .get()
                .toInt()
        versionName = "0.0.0"
        versionCode = 1
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                "proguard-rules.pro",
                getDefaultProguardFile("proguard-android-optimize.txt"),
            )
        }
    }

    lint {
        disable.add("UnusedAttribute") // workaround for android:enableOnBackInvokedCallback="true"
        warningsAsErrors = true
        checkAllWarnings = true
        abortOnError = true
        ignoreWarnings = false
        ignoreTestSources = false
        checkTestSources = true
    }
}

baselineProfile {
    dexLayoutOptimization = true
}

dependencies {
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlinx.date.time)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.profile.installer)
    implementation(libs.androidx.splash.screen)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    implementation(projects.app.proto)
    implementation(libs.androidx.datastore)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.performance) {
        // Workaround for issue: https://github.com/firebase/firebase-android-sdk/issues/5997
        exclude(
            module = "protolite-well-known-types",
        )
    }

    implementation(libs.play.services.ads)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    baselineProfile(projects.benchmark)
}
