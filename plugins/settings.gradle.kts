import java.nio.file.Paths

rootProject.name = "Plugins"

dependencyResolutionManagement {
    val toml =
        Paths
            .get(rootDir.path)
            .parent
            .resolve("gradle")
            .resolve("libs.versions.toml")
            .let {
                files(it)
            }
    versionCatalogs
        .create("libs")
        .from(files(toml))
}
