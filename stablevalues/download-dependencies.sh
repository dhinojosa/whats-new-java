#!/bin/bash

set -e

# Create lib directory if it doesn't exist
mkdir -p lib
rm -f lib/*.jar

# Download dependencies with error checking
echo "Downloading dependencies..."
curl -f -L -o lib/slf4j-api-2.0.9.jar https://repo1.maven.org/maven2/org/slf4j/slf4j-api/2.0.9/slf4j-api-2.0.9.jar || exit 1
curl -f -L -o lib/log4j-api-2.21.1.jar https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-api/2.21.1/log4j-api-2.21.1.jar || exit 1
curl -f -L -o lib/log4j-core-2.21.1.jar https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-core/2.21.1/log4j-core-2.21.1.jar || exit 1
curl -f -L -o lib/log4j-slf4j2-impl-2.21.1.jar https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-slf4j2-impl/2.21.1/log4j-slf4j2-impl-2.21.1.jar || exit 1

echo "All dependencies downloaded successfully"
