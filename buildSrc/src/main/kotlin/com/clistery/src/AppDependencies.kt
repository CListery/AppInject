package com.clistery.src

object AppDependencies {
    
    object clistery{
        const val appbasic = "io.github.clistery:appbasic:2.2.0"
    }
    
    object kotlin {
        
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${AppVersion.kotlin.version}"
    }
    
    object androidx {
        
        const val coreKtx = "androidx.core:core-ktx:${AppVersion.androidx.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${AppVersion.androidx.appcompat}"
        const val viewbinding = "androidx.databinding:viewbinding:4.2.1"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:2.3.1"
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
    }
    
    object google {
        
        const val material = "com.google.android.material:material:${AppVersion.google.material}"
    }
    
    val baseLibs: ArrayList<String>
        get() = arrayListOf(
            kotlin.stdlib,
            androidx.coreKtx,
            androidx.appcompat,
        )
    
}
