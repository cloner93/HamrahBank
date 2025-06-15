package com.pmb.network

import android.annotation.SuppressLint
import android.util.Log
import com.pmb.core.network.safeRequestCall
import com.pmb.core.platform.Result
import com.pmb.network.dto.MobileApiRequest
import com.pmb.network.dto.RequestMetaData
import com.pmb.network.plugin.installErrorHandler
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import javax.inject.Inject

class NetworkManger @Inject constructor(
    private val userSession: UserSessionImpl
) {
    val client: HttpClient = HttpClient(Android) {
        defaultRequest {
            host = "172.20.140.242:9080/api/v1"
            url {
                protocol = URLProtocol.HTTP
            }
            // we can add header for each request.
            headers {
                append("Accept", "application/json")
                append("Content-Type", "application/json")
            }
        }

        engine {
            sslManager = {
                it.sslSocketFactory = SslSettings.getSslContext()?.socketFactory
            }
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("ktor", message)
                }
            }
//            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 20_000
            connectTimeoutMillis = 20_000
            socketTimeoutMillis = 20_000
        }

        installErrorHandler()
    }

    inline fun <reified T, reified R> request(
        endpoint: String,
        data: T,
        queryParams: Map<String, String> = emptyMap()
    ): Flow<Result<R>> {
        val request = MobileApiRequest(
            // this data is temporary
            metaData = RequestMetaData(
                initialVec = "",
                imei = "12345",
                osType = 7,
                osVersion = 1,
                deviceName = "google pixel 9 pro"
            ),
            data = data,
        )

        return safeRequestCall {
            client.post {
                url {
                    path(endpoint)

                    queryParams.forEach { (key, value) ->
                        parameters.append(key, value)
                    }
                }
                setBody(request)
            }.body()
        }
    }
}


@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class RefreshRequest(val refresh_token: String)

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class TokenResponse(val access_token: String, val refresh_token: String)

data class SessionData(var accessToken: String, var refreshToken: String)