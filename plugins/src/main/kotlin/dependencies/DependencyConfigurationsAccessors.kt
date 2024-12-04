package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}

internal fun DependencyHandler.ksp(dependencyNotation: Any) {
    add("ksp", dependencyNotation)
}

internal fun DependencyHandler.kspAndroidTest(dependencyNotation: Any) {
    add("kspAndroidTest", dependencyNotation)
}

internal fun DependencyHandler.androidTestImplementation(dependencyNotation: Any) {
    add("androidTestImplementation", dependencyNotation)
}

internal fun DependencyHandler.debugImplementation(dependencyNotation: Any) {
    add("debugImplementation", dependencyNotation)
}

internal fun DependencyHandler.androidTestUtil(dependencyNotation: Any) {
    add("androidTestUtil", dependencyNotation)
}
