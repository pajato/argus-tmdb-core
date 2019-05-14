pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "kotlin-multiplatform") {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
            }
            if (requested.id.id == "kotlinx-serialization") {
                useModule("org.jetbrains.kotlin:kotlin-serialization:${requested.version}")
            }
            if (requested.id.id == "com.jfrog.bintray") {
                useModule("com.jfrog.bintray.gradle:gradle-bintray-plugin:${requested.version}")
            }
        }
    }
    repositories {
        if (Kotlin.repo.isNotEmpty()) maven { url = uri(Kotlin.repo) }
        maven { url = uri(Bintray.repo) }
        mavenCentral()
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }
}

rootProject.name = "argus-tmdb-core"

include(":argus-tmdb-core")

enableFeaturePreview("GRADLE_METADATA")
