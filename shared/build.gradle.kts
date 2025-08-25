plugins {
	id("org.jetbrains.kotlin.multiplatform")
	alias(libs.plugins.android.library)
	id("org.jetbrains.kotlin.plugin.serialization")
}

kotlin {
	androidTarget()

	iosX64()

	iosArm64()

	iosSimulatorArm64()

	sourceSets {
		commonMain {
			dependencies {
				implementation(libs.kotlinx.coroutines.core)
				implementation(libs.ktor.client.core)
				implementation(libs.ktor.client.auth)
				implementation(libs.ktor.client.logging)
				implementation(libs.ktor.client.content.negotiation)
				implementation(libs.ktor.serialization.kotlinx.json)
				implementation(libs.kotlinx.serialization.json)
			}
		}
		androidMain {
			dependencies {
				implementation(libs.ktor.client.android)
			}
		}
		iosMain {
			dependencies {
				implementation(libs.ktor.client.darwin)
			}
		}
	}
}

android {
	namespace = "com.pmb.shared"
	compileSdk = 35
	defaultConfig {
		minSdk = 24
		targetSdk = 35
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_19
		targetCompatibility = JavaVersion.VERSION_19
	}
}