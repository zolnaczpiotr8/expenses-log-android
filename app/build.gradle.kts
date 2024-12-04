import application.id.APPLICATION_ID

plugins {
    alias(libs.plugins.expenses.log.application)
    alias(libs.plugins.androidx.baseline.profile)
}

android {
    defaultConfig {
        testInstrumentationRunner = "zolnaczpiotr8.com.github.expenses.log.app.runner.HiltRunner"
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

    lint {
        disable.add("UnusedAttribute") // workaround for android:enableOnBackInvokedCallback="true"
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
    implementation(projects.feature.expense)
    implementation(projects.feature.settings)
    implementation(projects.core.ui)

    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(projects.core.datastore.testDoubles)
    kspAndroidTest(libs.hilt.android.compiler)

    baselineProfile(projects.benchmark)
}
