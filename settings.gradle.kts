pluginManagement {
    repositories {
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "atomicfu-kmp-benchmark"


include(":atomicfu-user")
include(":benchmarks")
