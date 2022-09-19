import com.clistery.src.AppDependencies

plugins {
    id("kre-publish")
}

android {
    buildTypes.configureEach {
        isMinifyEnabled = false
        isDebuggable = false
    }
}

dependencies {
    AppDependencies.baseLibs.forEach { implementation(it) }
    implementation(AppDependencies.clistery.appbasic)
    compileOnly(AppDependencies.androidx.viewbinding)
}
