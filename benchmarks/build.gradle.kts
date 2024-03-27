import kotlinx.benchmark.gradle.JvmBenchmarkTarget
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask

repositories {
    mavenCentral()
    mavenLocal()
}

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.kotlinx.benchmark")
}

kotlin {
    jvm()
    macosArm64()
    js(IR) { nodejs() }

    sourceSets {
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.10")
                implementation(project(":atomicfu-user"))
            }
        }
        jsMain {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlinx-atomicfu-runtime:1.9.22")
                // Uncomment me to fix the issue
                api("org.jetbrains.kotlinx:atomicfu:0.23.2")
            }
        }
    }
}

benchmark {
    targets {
        register("js")
        register("macosArm64")
        register("jvm") {
            this as JvmBenchmarkTarget
            jmhVersion = "1.37"
        }
    }
}

rootProject.the<NodeJsRootExtension>().apply {
    nodeVersion = "21.0.0-v8-canary202310177990572111"
    nodeDownloadBaseUrl = "https://nodejs.org/download/v8-canary"
}

rootProject.tasks.withType<KotlinNpmInstallTask>().configureEach {
    args.add("--ignore-engines")
}
