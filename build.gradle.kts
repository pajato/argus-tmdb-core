// SDPX-License-Identifier: LGPL-3.0

plugins {
    kotlin("multiplatform") version Kotlin.version apply false
    id("kotlinx-serialization") version Kotlin.version apply false
    id("com.jfrog.bintray") version Bintray.version apply false
    jacoco
}

allprojects {
    repositories {
        if (Kotlin.repo.isNotEmpty()) maven { url = uri(Kotlin.repo) }
        jcenter()
    }
}

task("clean") {
    delete(rootProject.buildDir)
}
