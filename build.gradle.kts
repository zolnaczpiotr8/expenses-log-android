import com.diffplug.spotless.extra.wtp.EclipseWtpFormatterStep
import com.diffplug.spotless.shell.ShfmtStep

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.android.gradle)
        classpath(libs.kotlin.compose)
        classpath(libs.kotlin.gradle)
        classpath(libs.room)
        classpath(libs.hilt.android.gradle)
    }
}

plugins {
    alias(libs.plugins.spotless)
    alias(libs.plugins.git.hooks)
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.serialization) apply false
}

spotless {
    kotlin {
        target("**/*.kt")
        ktlint().editorConfigOverride(
            mapOf("android" to "true"),
        )
    }
    kotlinGradle {
        target("**/*.kts")
        ktlint()
    }
    format("xml") {
        target("**/*.xml")
        targetExclude(".idea/")
        eclipseWtp(EclipseWtpFormatterStep.XML)
    }
    json {
        target("**/*.json")
        simple()
    }
    shell {
        target("**/*.sh")
        ignoreErrorForStep(ShfmtStep.name())
        shfmt("v3.5.1")
    }
}
