package dependencies

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension

internal fun Project.libs(alias: String): String = versionCatalog
    .findLibrary(alias)
    .get()
    .get()
    .toString()

internal fun Project.targetSdk(): Int = versions("target.sdk").toInt()

internal fun Project.versions(alias: String): String = versionCatalog
    .findVersion(alias)
    .get()
    .toString()

private val Project.versionCatalog
    get() =
        rootProject
            .extensions
            .getByType(VersionCatalogsExtension::class.java)
            .named("libs")
