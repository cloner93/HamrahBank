package com.pmb.shared.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

expect fun platformHttpClient(): HttpClient

fun createCommonHttpClient(): HttpClient =
	platformHttpClient().config {
		install(ContentNegotiation) {
			json(
				Json {
					ignoreUnknownKeys = true
					isLenient = true
					encodeDefaults = true
				}
			)
		}
	}