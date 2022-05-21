import com.clistery.src.AppConfig
import com.clistery.src.AppDependencies
import com.clistery.src.implementation

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    defaultConfig {
        applicationId = "com.yh.demo"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode(AppConfig.versionCode)
        versionName(AppConfig.versionName)
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
    implementation(AppDependencies.androidx.livedata)
    implementation(AppDependencies.androidx.viewmodel)
    implementation(AppDependencies.google.material)
    implementation(AppDependencies.clistery.appbasic)
    implementation(project(mapOf("path" to ":libapp")))
    implementation(project(mapOf("path" to ":lib_appinject")))
}
