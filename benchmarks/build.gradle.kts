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
                implementation(project(":ir-disabled"))
                implementation(project(":ir-enabled"))
            }
        }
        jsMain {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlinx-atomicfu-runtime:1.9.22")
                // Uncomment me to fix the issue
                // api("org.jetbrains.kotlinx:atomicfu:0.23.2")
            }
        }
    }
}

benchmark {
    targets {
        register("js")
        register("macosArm64")
    }
}
