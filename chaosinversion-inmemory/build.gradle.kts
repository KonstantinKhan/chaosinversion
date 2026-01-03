plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotest)
}

dependencies {
    implementation(projects.chaosinversionDomainModels)
    implementation(projects.chaosinversionTransportModels)

    implementation(libs.ehcache)
    implementation(libs.kotlinx.coroutines.core)

    testImplementation(libs.kotest.engine)
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.kotlinx.coroutines.test)
}