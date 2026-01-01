@file:Suppress("UnstableApiUsage")

rootProject.name = "chaosinversion"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include("chaosinversion-ktor-app")
include("chaosinversion-transport-models")
include("chaosinversion-domain-models")
include("chaosinversion-model-mappers")
include("chaosinversion-project-service")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
