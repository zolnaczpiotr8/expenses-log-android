#!/bin/sh

./gradlew --init-script init.gradle.kts \
  wrapper --gradle-version latest \
  spotlessCheck \
  lint

./gradlew --init-script init.gradle.kts \
  clean \
  connectedAndroidTest
