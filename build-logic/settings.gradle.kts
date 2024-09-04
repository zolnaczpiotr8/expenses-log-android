import java.nio.file.Paths

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            val path =
                Paths
                    .get(rootDir.path)
                    .parent
                    .resolve("gradle")
                    .resolve("libs.versions.toml")
                    .toString()
            from(files(path))
        }
    }
}

rootProject.name = "Build-logic"

include(
    ":convention",
)
