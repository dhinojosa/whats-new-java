# Use a base image with a Linux distribution
FROM mcr.microsoft.com/vscode/devcontainers/base:ubuntu

# Install dependencies
RUN apt-get update && apt-get install -y \
    curl \
    tar \
    unzip \
    ca-certificates \
    && apt-get clean -y && rm -rf /var/lib/apt/lists/*

USER root

# Download and install OpenJDK 25
RUN curl -L -o openjdk-25_linux-x64_bin.tar.gz https://download.java.net/java/GA/jdk25/bd75d5f9689641da8e1daabeccb5528b/36/GPL/openjdk-25_linux-x64_bin.tar.gz \
    && tar -xzf openjdk-25_linux-x64_bin.tar.gz -C /usr/local/ \
    && rm openjdk-25_linux-x64_bin.tar.gz \
    && mv /usr/local/jdk-25 /usr/local/openjdk-25

# Set JAVA_HOME environment variable
ENV JAVA_HOME=/usr/local/openjdk-25
ENV PATH=${JAVA_HOME}/bin:${PATH}

# Download and install Maven
RUN MAVEN_VERSION=3.9.8 \
    && MAVEN_BASE_URL=https://archive.apache.org/dist/maven/maven-$(echo $MAVEN_VERSION | cut -d'.' -f1) \
    && curl -fL -o /tmp/apache-maven-bin.tar.gz ${MAVEN_BASE_URL}/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz \
    && tar -xzf /tmp/apache-maven-bin.tar.gz -C /usr/local/ \
    && rm /tmp/apache-maven-bin.tar.gz \
    && mv /usr/local/apache-maven-$MAVEN_VERSION /usr/local/maven

# Set MAVEN_HOME environment variable
ENV MAVEN_HOME=/usr/local/maven
ENV PATH=${MAVEN_HOME}/bin:${PATH}

# Switch to vscode user
USER vscode
