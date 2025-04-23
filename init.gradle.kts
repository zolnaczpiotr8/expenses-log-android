beforeSettings {
    pluginManagement {
        repositories {
            google()
            mavenCentral()
            gradlePluginPortal()
        }
    }

    dependencyResolutionManagement {
        repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
        repositories {
            google()
            mavenCentral()
        }
    }
}

afterProject {
    extensions.findByType<JavaPluginExtension>()?.let {
        it.toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        options.compilerArgs.addAll(
            listOf(
                "-Xlint:all",
            ),
        )
    }
}
