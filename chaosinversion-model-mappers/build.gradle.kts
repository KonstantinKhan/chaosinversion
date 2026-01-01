plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

dependencies {
    implementation(projects.chaosinversionDomainModels)
    implementation(projects.chaosinversionTransportModels)

    testImplementation(libs.kotlin.test.junit)
}