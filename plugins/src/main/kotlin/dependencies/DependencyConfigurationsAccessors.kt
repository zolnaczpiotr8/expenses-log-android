package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}

fun DependencyHandler.ksp(dependencyNotation: Any) {
    add("ksp", dependencyNotation)
}

fun DependencyHandler.kspAndroidTest(dependencyNotation: Any) {
    add("kspAndroidTest", dependencyNotation)
}

fun DependencyHandler.androidTestImplementation(dependencyNotation: Any) {
    add("androidTestImplementation", dependencyNotation)
}

fun DependencyHandler.debugImplementation(dependencyNotation: Any) {
    add("debugImplementation", dependencyNotation)
}

fun DependencyHandler.androidTestUtil(dependencyNotation: Any) {
    add("androidTestUtil", dependencyNotation)
}
