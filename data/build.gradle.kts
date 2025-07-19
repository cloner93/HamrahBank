plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.pmb.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = "19"
    }
    // Allow references to generated code
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    api(project(":network"))
    implementation(project(":domain"))
    implementation(project(":core"))
    implementation(project(":model"))

    api(libs.android.hilt)
    kapt(libs.android.hilt.compiler)
    implementation(libs.ktor.client.core)
    implementation(libs.kotlinx.serialization.json)

    // todo: temporary store them in datastore - move it to data layer
    implementation(libs.dataStore)

}