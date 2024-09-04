package zolnaczpiotr8.com.github.expenses.log.build.logic.plugins

import androidx.room.gradle.RoomExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import zolnaczpiotr8.com.github.expenses.log.build.logic.implementation
import zolnaczpiotr8.com.github.expenses.log.build.logic.ksp
import zolnaczpiotr8.com.github.expenses.log.build.logic.libs
import java.nio.file.Paths

class AndroidRoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("androidx.room")
                apply("com.google.dagger.hilt.android")
            }

            room {
                val schemaPath = Paths.get(projectDir.toString(), "schemas")
                schemaDirectory(schemaPath.toString())
            }

            dependencies {
                implementation(libs("room.runtime"))
                implementation(libs("room.ktx"))
                ksp(libs("room.compiler"))
            }
        }
    }

    private fun Project.room(configuration: RoomExtension.() -> Unit) {
        extensions.findByType<RoomExtension>()?.let {
            configuration.invoke(it)
        }
    }
}
