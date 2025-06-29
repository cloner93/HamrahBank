package com.pmb.network

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
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
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Inject
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

class NetworkManger @Inject constructor(
    private val userSession: UserSessionImpl
) {
    val client: HttpClient = HttpClient(Android) {
        defaultRequest {
            host = "172.20.140.242:8443/api/v1"
            url {
                protocol = URLProtocol.HTTPS
            }
            headers {
                append("Content-Type", "application/json")
            }
        }

        // temp
        engine {
            sslManager = { httpsURLConnection ->
                httpsURLConnection.hostnameVerifier = HostnameVerifier { _, _ -> true }
                httpsURLConnection.sslSocketFactory = SSLContext.getInstance("TLS")
                    .apply {
                        init(null, arrayOf(AllCertsTrustManager()), SecureRandom())
                    }.socketFactory
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
                osVersion = Build.VERSION.RELEASE.toIntOrNull() ?: 1,
                deviceName = "${Build.MANUFACTURER} ${Build.MODEL}"
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

class AllCertsTrustManager : X509TrustManager {

    override fun checkClientTrusted(
        chain: Array<out X509Certificate>?,
        authType: String?
    ) {
    }

    override fun checkServerTrusted(
        chain: Array<out X509Certificate>?,
        authType: String?
    ) {
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
}