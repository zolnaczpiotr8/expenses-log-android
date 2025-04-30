#!/bin/sh

set -e

./gradlew --init-script init.gradle.kts \
  wrapper --gradle-version latest \
  spotlessCheck \
  lint \
  clean

./gradlew --init-script init.gradle.kts \
  assembleDebug
