import java.io.FileNotFoundException
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.compose.compiler)
}


// Function to load signing.properties file
fun getSigningConfigProperties(): Properties {
    val properties = Properties()
    val file = file("../signing.properties")
    if (file.exists()) {
        properties.load(file.inputStream())
    } else {
        throw FileNotFoundException("signing.properties file not found in project root")
    }
    return properties
}

// Load the properties
val signingProperties = getSigningConfigProperties()

android {
    namespace = "com.pmb.mobile"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.pmb.mobile"
        minSdk = 24
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file(signingProperties["STORE_FILE_PATH"] as String)
            storePassword = signingProperties["STORE_PASSWORD"] as String
            keyAlias = signingProperties["KEY_ALIAS"] as String
            keyPassword = signingProperties["KEY_PASSWORD"] as String
        }

        getByName("debug") {
            storeFile = file(signingProperties["STORE_FILE_PATH"] as String)
            storePassword = signingProperties["STORE_PASSWORD"] as String
            keyAlias = signingProperties["KEY_ALIAS"] as String
            keyPassword = signingProperties["KEY_PASSWORD"] as String
        }
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        debug {
            applicationIdSuffix = ".n"
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = "19"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":navigation"))
    implementation(project(":ballon"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(projects.feature.auth)
    implementation(projects.feature.home)
    implementation(projects.feature.transfer)
    implementation(projects.feature.account)
    implementation(projects.feature.profile)

    // DI > Hilt
    implementation(libs.android.hilt)
    kapt(libs.android.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}