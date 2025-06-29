package com.pmb.network

import android.content.Context
import java.security.KeyStore
import java.security.cert.CertificateFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

@Deprecated("This method is temporary. use real ssl pinning instead")
object SslSettings {
    fun getTrustManagerFactory(context: Context): TrustManagerFactory {
        val certificate = context.assets.open("crt/cert.cer").use { inputStream ->
            CertificateFactory.getInstance("X.509").generateCertificate(inputStream)
        }

        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType()).apply {
            load(null, null)
            setCertificateEntry("ca", certificate)
        }

        return TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()).apply {
            init(keyStore)
        }
    }

    fun getTrustManager(context: Context): X509TrustManager {
        val trustManagers = getTrustManagerFactory(context).trustManagers
        return trustManagers.first { it is X509TrustManager } as X509TrustManager
    }

    fun getSslContext(context: Context): SSLContext {
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, getTrustManagerFactory(context).trustManagers, null)
        return sslContext
    }
}