buildscript {

    val kotlin_version by extra("1.4.32")
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
        classpath("com.android.tools.build:gradle:4.2.0-rc01")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${properties["versionsHilt"]}")
        classpath("com.google.gms:google-services:4.3.3")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://dl.bintray.com/netguru/maven/")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}