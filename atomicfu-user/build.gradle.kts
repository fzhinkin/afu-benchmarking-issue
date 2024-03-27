import kotlinx.atomicfu.plugin.gradle.AtomicFUPluginExtension
import kotlinx.atomicfu.transformer.main

repositories {
    mavenCentral()
}

apply(plugin = "kotlinx-atomicfu")

plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()
    macosArm64()
    js(IR) { nodejs() }

    sourceSets {
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

project.extensions.getByType(AtomicFUPluginExtension::class).apply {
    transformJs = true
}
