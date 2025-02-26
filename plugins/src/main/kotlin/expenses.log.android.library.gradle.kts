import conventions.configureAndroid
import conventions.configureKotlin

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

configureAndroid()
configureKotlin()
