import com.clistery.src.AppDependencies

plugins {
    id("app")
}

dependencies {
    AppDependencies.baseLibs.forEach { implementation(it) }
    implementation(AppDependencies.androidx.appcompat)
    implementation(AppDependencies.androidx.livedata)
    implementation(AppDependencies.androidx.viewmodel)
    implementation(AppDependencies.google.material)
    implementation(AppDependencies.clistery.appbasic)
    implementation(project(mapOf("path" to ":libapp")))
    implementation(project(mapOf("path" to ":lib_appinject")))
}
