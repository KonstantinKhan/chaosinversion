plugins {
    alias(libs.plugins.kotlin.jvm) apply false
}

group = "com.khan366kos"
version = "0.0.1"

subprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenCentral()
    }

    if (plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
        configure<org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension> {
            jvmToolchain(21)
        }
    }
}