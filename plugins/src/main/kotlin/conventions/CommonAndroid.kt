package conventions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

fun Project.commonAndroid(configuration: CommonExtension<*, *, *, *, *, *>.() -> Unit) {
    extensions
        .findByType(CommonExtension::class.java)
        ?.let(configuration::invoke)
}
