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
            api(project(":network"))
            implementation(project(":domain"))
            implementation(project(":model"))
            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.serialization.json)
        }
        
        androidMain.dependencies {
            implementation(libs.androidx.paging.common.android)
            implementation(libs.dataStore)
            implementation(projects.calender)
        }
        
        commonTest.dependencies {
            implementation(libs.junit)
        }
    }
}

android {
    namespace = "com.pmb.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
}