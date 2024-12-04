plugins {
    alias(libs.plugins.expenses.log.ksp.library)
    alias(libs.plugins.expenses.log.hilt)
    alias(libs.plugins.room)
}

room {
    schemaDirectory(projectDir.resolve("schemas").toString())
}

dependencies {
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.kotlinx.date.time)
    ksp(libs.room.compiler)
}
