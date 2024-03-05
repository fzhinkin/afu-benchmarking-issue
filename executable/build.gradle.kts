repositories {
    mavenCentral()
}

plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()
    js(IR) {
        nodejs()
        binaries.executable()
    }
    macosArm64()

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":ir-enabled"))
            }
        }
    }
}
