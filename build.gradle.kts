// SDPX-License-Identifier: LGPL-3.0

plugins {
    kotlin("multiplatform") version Versions.kotlin apply false
    id("kotlinx-serialization") version Versions.kotlin apply false
}

allprojects {
    repositories {
        jcenter()
    }

    tasks.register("cleanProject") {
        delete(project.buildDir)
    }
}
