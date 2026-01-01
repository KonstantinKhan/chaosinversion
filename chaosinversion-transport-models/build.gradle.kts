plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

dependencies {
    implementation(libs.kotlin.serialization.json)

    testImplementation(libs.kotlin.test.junit)
}