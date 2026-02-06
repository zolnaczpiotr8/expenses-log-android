import com.diffplug.spotless.extra.wtp.EclipseWtpFormatterStep
import java.nio.file.Files
import kotlin.io.path.Path

plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.test) apply false
  alias(libs.plugins.kotlin.jvm) apply false
  alias(libs.plugins.protobuf) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.compose) apply false
  alias(libs.plugins.kotlin.serialization) apply false
  alias(libs.plugins.androidx.baseline.profile) apply false
  alias(libs.plugins.ksp) apply false
  alias(libs.plugins.room) apply false
  alias(libs.plugins.hilt.android) apply false
  alias(libs.plugins.compose.screenshot) apply false
}

spotless {
  val ignoredFiles by lazy { Path(".gitignore").run(Files::readAllLines).toTypedArray() }

  kotlin {
    target("**/*.kt")
    targetExclude(*ignoredFiles)
    ktfmt()
  }
  kotlinGradle {
    target("**/*.kts")
    targetExclude(*ignoredFiles)
    ktfmt()
  }
  format("xml") {
    target("**/*.xml")
    targetExclude(*ignoredFiles)
    eclipseWtp(EclipseWtpFormatterStep.XML)
  }
}
