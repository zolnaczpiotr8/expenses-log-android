plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.androidx.baseline.profile)
  alias(libs.plugins.ksp)
  alias(libs.plugins.room)
  alias(libs.plugins.hilt.android)
  alias(libs.plugins.compose.screenshot)
}

composeCompiler {
  stabilityConfigurationFiles.add(
      layout.projectDirectory.file("compose.conf"),
  )
}

room { schemaDirectory("database-schema") }

kotlin {
  jvmToolchain(21)
  compilerOptions {
    optIn.addAll(
        "androidx.compose.material3.ExperimentalMaterial3Api",
        "kotlinx.coroutines.ExperimentalCoroutinesApi",
    )
    allWarningsAsErrors.set(true)
  }
}

screenshotTests { imageDifferenceThreshold = 0.0001f }

android {
  ndkVersion = libs.versions.ndk.get()
  experimentalProperties["android.experimental.enableScreenshotTest"] = true

  buildFeatures { compose = true }

  androidResources {
    generateLocaleConfig = true
    localeFilters.apply {
      add("pl")
      add("af")
      add("sq")
      add("am")
      add("ar")
      add("hy")
      add("as")
      add("az")
      add("bm")
      add("ba")
      add("eu")
      add("be")
      add("bn")
      add("bs")
      add("bg")
      add("my")
      add("ca")
      add("ceb")
      add("ny")
      add("b+zh+Hans")
      add("b+zh+Hant")
      add("co")
      add("hr")
      add("cs")
      add("da")
      add("nl")
      add("eo")
      add("et")
      add("fil")
      add("fi")
      add("fr")
      add("fy")
      add("gl")
      add("ka")
      add("de")
      add("el")
      add("gu")
      add("ht")
      add("ha")
      add("iw")
      add("hi")
      add("hu")
      add("is")
      add("in")
      add("ga")
      add("it")
      add("ja")
      add("jv")
      add("kn")
      add("kk")
      add("km")
      add("ko")
      add("lv")
      add("lt")
      add("mk")
      add("ms")
      add("ml")
      add("mt")
      add("mr")
      add("mn")
      add("ne")
      add("no")
      add("ps")
      add("fa")
      add("pt")
      add("pa")
      add("ro")
      add("ru")
      add("sa")
      add("sr")
      add("si")
      add("sk")
      add("sl")
      add("es")
      add("su")
      add("sw")
      add("sv")
      add("tg")
      add("ta")
      add("te")
      add("th")
      add("tr")
      add("uk")
      add("ur")
      add("uz")
      add("vi")
    }

    testOptions { execution = "ANDROIDX_TEST_ORCHESTRATOR" }
  }

  compileSdk = libs.versions.target.sdk.get().toInt()
  namespace = "zolnaczpiotr8.com.github.expenses.log"

  defaultConfig {
    minSdk = 26
    targetSdk = libs.versions.target.sdk.get().toInt()
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    testInstrumentationRunnerArguments["clearPackageData"] = "true"
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      isShrinkResources = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
  warnings.setAll(true)
}

dependencies {
  implementation(libs.kotlinx.serialization)

  implementation(libs.androidx.profile.installer)
  implementation(libs.androidx.splash.screen)

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.material.icons.extended)

  implementation(libs.androidx.navigation.compose)
  implementation(libs.androidx.hilt.lifecycle.viewmodel.compose)

  implementation(libs.room.runtime)
  implementation(libs.room.ktx)
  ksp(libs.room.compiler)

  implementation(projects.app.proto)
  implementation(libs.androidx.datastore)

  implementation(libs.hilt.android)
  ksp(libs.hilt.android.compiler)

  baselineProfile(projects.benchmark)

  androidTestImplementation(libs.turbine)
  androidTestImplementation(libs.kotlinx.coroutines.test)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.compose.test.junit4)
  androidTestImplementation(libs.hilt.android.testing)
  androidTestUtil(libs.androidx.test.orchestrator)
  debugImplementation(libs.androidx.compose.test.manifest)

  testImplementation(libs.junit)
  testImplementation(libs.kotlinx.coroutines.test)

  screenshotTestImplementation(platform(libs.androidx.compose.bom))
  screenshotTestImplementation(libs.androidx.compose.ui.tooling)
  screenshotTestImplementation(libs.screenshot.validation.api)
}
