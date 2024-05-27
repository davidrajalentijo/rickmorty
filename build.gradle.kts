// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.5"
}

allprojects {
    apply(from = "$rootDir/detekt.gradle")

    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.5")
    }
}