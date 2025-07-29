plugins {
  alias(libs.plugins.android.test)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.androidx.baseline.profile)
}

android {
  compileSdk = libs.versions.compile.sdk.get().toInt()
  namespace = "zolnaczpiotr8.com.github.expenses.log.benchmark"

  defaultConfig {
    minSdk = 28
    testInstrumentationRunner = "androidx.benchmark.junit4.AndroidBenchmarkRunner"
  }

  targetProjectPath = projects.app.path
  experimentalProperties["android.experimental.self-instrumenting"] = true
}

dependencies {
  implementation(libs.androidx.benchmark.macro)
  implementation(libs.androidx.benchmark.junit4)
}
