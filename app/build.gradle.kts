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

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.profileinstaller)

    baselineProfile(projects.benchmark)

    implementation(projects.core.ui)
    implementation(projects.core.model)
    implementation(projects.core.data)
    implementation(projects.core.database)
    implementation(projects.core.datastore)
    implementation(projects.core.testing)
    implementation(projects.feature.addExpense)
    implementation(projects.feature.settings)
    implementation(projects.feature.trash)
    implementation(projects.feature.expenses)
    implementation(projects.feature.helpAndFeedback)
}
