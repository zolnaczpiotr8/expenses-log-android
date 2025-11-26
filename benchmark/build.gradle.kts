plugins {
  alias(libs.plugins.android.test)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.androidx.baseline.profile)
}

android {
  ndkVersion = libs.versions.ndk.get()
  compileSdk = libs.versions.target.sdk.get().toInt()
  namespace = "zolnaczpiotr8.com.github.expenses.log.benchmark"

  defaultConfig {
    minSdk = 28
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  targetProjectPath = projects.app.path
  experimentalProperties["android.experimental.self-instrumenting"] = true

  lint {
    warningsAsErrors = true
    checkAllWarnings = true
    abortOnError = true
    ignoreWarnings = false
    ignoreTestSources = false
    checkTestSources = true
  }
}

kotlin {
  jvmToolchain(21)
  compilerOptions { allWarningsAsErrors.set(true) }
}

dependencies { implementation(libs.androidx.benchmark.macro) }
