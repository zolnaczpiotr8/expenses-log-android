import zolnaczpiotr8.com.github.expenses.log.build.logic.plugins.configurations.APPLICATION_ID

plugins {
    alias(libs.plugins.expenses.log.android.application)
    alias(libs.plugins.androidx.baseline.profile)
}

android {
    defaultConfig {
        applicationId = APPLICATION_ID
        versionName = "0.0.0"
        versionCode = 1
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
            )
        }
    }
}

baselineProfile {
    dexLayoutOptimization = true
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.profile.installer)
    implementation(libs.androidx.splash.screen)
    implementation(projects.feature.home)
    implementation(projects.core.ui)
    baselineProfile(projects.benchmark)
}
