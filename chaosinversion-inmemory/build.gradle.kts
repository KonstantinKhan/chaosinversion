plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.chaosinversionDomainModels)
    implementation(projects.chaosinversionTransportModels)

    implementation(libs.ehcache)
    implementation(libs.kotlinx.coroutines.core)

    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.kotlinx.coroutines.test)
}