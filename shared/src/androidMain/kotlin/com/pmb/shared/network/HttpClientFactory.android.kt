package com.pmb.shared.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android

actual fun platformHttpClient(): HttpClient = HttpClient(Android)