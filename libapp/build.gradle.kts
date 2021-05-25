import com.clistery.gradle.AppConfig
import com.clistery.gradle.AppDependencies
import com.clistery.gradle.implementation

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
}

android {
    compileSdkVersion(com.clistery.gradle.AppConfig.compileSdk)
    buildToolsVersion(com.clistery.gradle.AppConfig.buildToolsVersion)
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    defaultConfig {
        minSdkVersion(com.clistery.gradle.AppConfig.minSdk)
        targetSdkVersion(com.clistery.gradle.AppConfig.targetSdk)
        versionCode(com.clistery.gradle.AppConfig.versionCode)
        versionName(com.clistery.gradle.AppConfig.versionName)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

}

dependencies {
    implementation(AppDependencies.baseLibs)
    implementation(AppDependencies.androidx.appcompat)
    compileOnly(project(mapOf("path" to ":lib_appinject")))
}
