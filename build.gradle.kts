buildscript {
    repositories {
        mavenCentral()
        mavenLocal()
    }

    dependencies {
        classpath("org.jetbrains.kotlinx:atomicfu-gradle-plugin:0.23.2")
    }
}

plugins {
    kotlin("multiplatform") version "1.9.22" apply false
    id("org.jetbrains.kotlinx.benchmark") version "0.4.10" apply false
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

allprojects.apply {
    repositories {
        mavenCentral()
        mavenLocal()
    }
}
