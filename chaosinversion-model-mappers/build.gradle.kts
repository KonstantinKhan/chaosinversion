plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.kotest)
}

dependencies {
    implementation(projects.chaosinversionDomainModels)
    implementation(projects.chaosinversionTransportModels)

    testImplementation(libs.kotest.engine)
    testImplementation(libs.kotest.assertions.core)
}