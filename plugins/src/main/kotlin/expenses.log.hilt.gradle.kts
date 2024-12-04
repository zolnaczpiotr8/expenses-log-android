import dependencies.implementation
import dependencies.ksp
import dependencies.libs

plugins {
    id("com.google.dagger.hilt.android")
}

dependencies {
    implementation(libs("hilt.android"))
    ksp(libs("hilt.android.compiler"))
}
