import kotlinx.atomicfu.plugin.gradle.AtomicFUPluginExtension

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
}

project.extensions.getByType(AtomicFUPluginExtension::class).apply {
    transformJs = true
}
