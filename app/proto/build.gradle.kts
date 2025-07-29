plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.protobuf)
}

protobuf {
  protoc { artifact = libs.protobuf.compiler.get().toString() }
  generateProtoTasks { all().configureEach { builtins { getByName("java") { option("lite") } } } }
}

dependencies { api(libs.protobuf.java.lite) }
