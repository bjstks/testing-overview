#!/usr/bin/env bash

set -eu

cd testing-overview
./gradlew test assemble
