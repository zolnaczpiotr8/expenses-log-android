#!/bin/sh

set -e

./gradlew spotlessCheck lint

./gradlew clean :app:assembleDebug