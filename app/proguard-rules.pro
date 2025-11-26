# Required due to known R8 issues affecting Java Lite Protobuf generated classes.
# Protobuf officially recommends this rule as a workaround. Remove once the issues are resolved.
# Related:
#   - https://chromium.googlesource.com/external/github.com/google/protobuf/+/HEAD/java/lite.md
-keep class * extends com.google.protobuf.GeneratedMessageLite { *; }