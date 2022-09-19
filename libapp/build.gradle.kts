import com.clistery.src.AppDependencies

plugins {
    id("lib")
}

android {
    buildTypes.configureEach {
        isMinifyEnabled = false
    }
}

dependencies {
    AppDependencies.baseLibs.forEach { implementation(it) }
    implementation(AppDependencies.clistery.appbasic)
    implementation(AppDependencies.androidx.appcompat)
    compileOnly(project(mapOf("path" to ":lib_appinject")))
}
