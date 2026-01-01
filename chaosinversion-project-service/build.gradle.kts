plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.chaosinversionDomainModels)
    implementation(projects.chaosinversionTransportModels)
    implementation(projects.chaosinversionModelMappers)

    testImplementation(libs.kotlin.test.junit)
}