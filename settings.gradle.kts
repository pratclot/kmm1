pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "kmm1"
include(":androidApp")
include(":shared")

enableFeaturePreview("GRADLE_METADATA")
