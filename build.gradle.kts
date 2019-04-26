plugins {
    kotlin("multiplatform") version "1.3.31"
    id("kotlinx-serialization") version "1.3.31"
    `maven-publish`
}

group = "com.pajato"
version = "0.0.3"

repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    mavenCentral()
    jcenter()
    mavenLocal()
    maven( "https://kotlin.bintray.com/kotlinx")
}

kotlin {

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.10.0")
            }
        }
        commonTest {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test-common")
                implementation("org.jetbrains.kotlin:kotlin-test-annotations-common")
            }
        }
        jvm("jvm").compilations["test"].defaultSourceSet {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test")
                implementation("org.jetbrains.kotlin:kotlin-test-junit")
            }
        }
    }
}
