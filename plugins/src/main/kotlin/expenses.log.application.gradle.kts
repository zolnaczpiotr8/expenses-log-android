import conventions.configureAndroid
import conventions.configureKotlin

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("expenses.log.hilt")
    id("expenses.log.navigation")
    id("expenses.log.compose")
}

configureAndroid()
configureKotlin()
