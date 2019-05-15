// This code is from Kevin Galligan's Stately project via Jake
// Wharton's (and friend) SQLDelight project albeit converted to Kotlin DSL.

plugins {
   id("maven-publish")
   id("apply plugin: 'signing")
}

group = Publish.group
version = Versions.argus

fun isReleaseBuild(): Boolean {
   return !Versions.argus.contains("SNAPSHOT")
}

val releaseRepositoryUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2/"
val snapshotRepositoryUrl = "https://oss.sonatype.org/content/repositories/snapshots/"
val repositoryUsername = "${project.property("SONATYPE_NEXUS_USERNAME")}"
val repositoryPassword = "${project.property("SONATYPE_NEXUS_PASSWORD")}"

tasks.register<Jar>("emptySourcesJar") { archiveClassifier.set("sources") }

tasks.register<Jar>("emptyJavadocJar") { archiveClassifier.set("javadoc") }

/* publishing {
   repositories {
      maven {
         url  = uri( if (isReleaseBuild()) releaseRepositoryUrl else snapshotRepositoryUrl )
         credentials {
            username = repositoryUsername
            password = repositoryPassword
         }
      }
      maven {
         name = "test"
         url  = uri("file://${rootProject.buildDir}/localMaven")
      }
   }
} */
