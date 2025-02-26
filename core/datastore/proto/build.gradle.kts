plugins {
    alias(libs.plugins.expenses.log.android.library)
    alias(libs.plugins.protobuf)
}

protobuf {
    protoc {
        artifact =
            libs.protobuf.compiler
                .get()
                .toString()
    }

    generateProtoTasks {
        all().configureEach {
            builtins {
                register("java") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    api(libs.protobuf.kotlin.lite)
}
