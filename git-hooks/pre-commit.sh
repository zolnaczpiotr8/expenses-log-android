#!/bin/sh
set -ex

./gradlew clean
./gradlew dependencyGuard
./gradlew spotlessCheck
./gradlew lint
./gradlew assembleDebug
./gradlew testDebugUnitTest
./gradlew allDevicesDebugAndroidTest
./gradlew :benchmark:connectedBenchmarkReleaseAndroidTest
