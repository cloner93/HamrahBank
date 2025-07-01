plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.kotlin.kapt)
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
    implementation(project(":network"))
    implementation(project(":domain"))
    implementation(project(":core"))
    implementation(project(":model"))

    api(libs.android.hilt)
    kapt(libs.android.hilt.compiler)
    implementation(libs.ktor.client.core)

    // todo: temporary store them in datastore - move it to data layer
    implementation(libs.dataStore)

}