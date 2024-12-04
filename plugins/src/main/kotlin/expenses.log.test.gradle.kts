import conventions.configureAndroid
import conventions.configureKotlin

plugins {
    id("com.android.test")
    id("org.jetbrains.kotlin.android")
}

configureAndroid()
configureKotlin()
