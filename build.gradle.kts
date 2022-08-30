// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.clistery.gradle")
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.1")
    
        classpath("org.jfrog.buildinfo:build-info-extractor-gradle:4.23.4") //artifactory
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.4.32") //dokka
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts.kts files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
