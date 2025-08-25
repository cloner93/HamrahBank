plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.android.library)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "19"
            }
        }
    }
    
    jvm("desktop")
    
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
    sourceSets {
        commonMain.dependencies {
            api(project(":model"))
            api(project(":domain"))
            api(project(":network"))
            api(project(":data"))
            implementation(libs.kotlinx.serialization.json)
        }
        
        androidMain.dependencies {
            // Android-specific dependencies will be handled by individual modules
        }
        
        val desktopMain by getting {
            dependencies {
                // Desktop-specific dependencies
            }
        }
        
        iosMain.dependencies {
            // iOS-specific dependencies
        }
        
        commonTest.dependencies {
            implementation(libs.junit)
        }
    }
}

android {
    namespace = "com.pmb.shared"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
}