#!/bin/sh
set -ex

./gradlew lint
./gradlew spotlessCheck
./gradlew clean
./gradlew assembleDebug
./gradlew testDebugUnitTest
./gradlew allDevicesDebugAndroidTest
./gradlew :benchmark:connectedBenchmarkReleaseAndroidTest
