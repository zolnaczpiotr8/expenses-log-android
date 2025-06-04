import com.diffplug.spotless.extra.wtp.EclipseWtpFormatterStep
import java.nio.file.Files
import kotlin.io.path.Path

plugins {
    alias(libs.plugins.spotless)
    alias(libs.plugins.git.hooks)
    alias(libs.plugins.expenses.log.android.library) apply false
    alias(libs.plugins.expenses.log.ksp.library) apply false
    alias(libs.plugins.expenses.log.hilt) apply false
    alias(libs.plugins.expenses.log.feature) apply false
}

spotless {
    val ignoredFiles by lazy {
        Path(".gitignore")
            .run(Files::readAllLines)
            .toTypedArray()
    }

    kotlin {
        target("**/*.kt")
        targetExclude(*ignoredFiles)
        ktlint().editorConfigOverride(
            mapOf("android" to "true"),
        )
    }
    kotlinGradle {
        target("**/*.kts")
        targetExclude(*ignoredFiles)
        ktlint()
    }
    format("xml") {
        target("**/*.xml")
        targetExclude(*ignoredFiles)
        eclipseWtp(EclipseWtpFormatterStep.XML)
    }
}
