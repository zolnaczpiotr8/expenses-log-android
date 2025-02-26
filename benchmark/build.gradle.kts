import application.id.APPLICATION_ID

plugins {
    alias(libs.plugins.expenses.log.test)
    alias(libs.plugins.androidx.baseline.profile)
}

android {
    defaultConfig {
        minSdk = 28
        testInstrumentationRunner = "androidx.benchmark.junit4.AndroidBenchmarkRunner"
    }

    targetProjectPath = projects.app.path
    experimentalProperties["android.experimental.self-instrumenting"] = true

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField(
            type = "String",
            name = "APPLICATION_ID",
            value = "\"$APPLICATION_ID\"",
        )
    }
}

dependencies {
    implementation(libs.androidx.benchmark.macro)
    implementation(libs.androidx.benchmark.junit4)
}
