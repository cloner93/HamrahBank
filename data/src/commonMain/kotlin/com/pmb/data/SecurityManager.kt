package com.pmb.data

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.nio.charset.Charset
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

@Singleton
class SecurityManager @Inject constructor() {
    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }

    private val standardKeyAlias = "standard_key"

    init {
        createStandardKeyIfNotExists()
    }

    private fun createStandardKeyIfNotExists() {
        if (keyStore.containsAlias(standardKeyAlias)) return

        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,
            "AndroidKeyStore"
        )
        val parameterSpec = KeyGenParameterSpec.Builder(
            standardKeyAlias,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .build()

        keyGenerator.init(parameterSpec)
        keyGenerator.generateKey()
    }

    data class EncryptedData(val ciphertext: String, val iv: String)

    fun encrypt(data: String): EncryptedData {
        val keyAlias = standardKeyAlias
        val secretKey = getSecretKey(keyAlias)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val iv = cipher.iv
        val encryptedBytes = cipher.doFinal(data.toByteArray(Charset.defaultCharset()))
        return EncryptedData(
            ciphertext = Base64.encodeToString(encryptedBytes, Base64.DEFAULT),
            iv = Base64.encodeToString(iv, Base64.DEFAULT)
        )
    }

    fun decrypt(encryptedData: EncryptedData): String? {

        return try {
            val secretKey = getSecretKey(standardKeyAlias)
            val cipher = Cipher.getInstance("AES/GCM/NoPadding")
            val iv = Base64.decode(encryptedData.iv, Base64.DEFAULT)
            val spec = GCMParameterSpec(128, iv)
            cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)
            val decodedBytes = Base64.decode(encryptedData.ciphertext, Base64.DEFAULT)
            val decryptedBytes = cipher.doFinal(decodedBytes)
            String(decryptedBytes, Charset.defaultCharset())
        } catch (e: Exception) {
            null
        }
    }

    private fun getSecretKey(alias: String): SecretKey {
        return (keyStore.getEntry(alias, null) as KeyStore.SecretKeyEntry).secretKey
    }
}