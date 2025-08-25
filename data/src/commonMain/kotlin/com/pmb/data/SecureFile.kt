package com.pmb.data

import android.content.Context
import com.pmb.core.utils.getAndroidId
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class SecureFile(
    val context: Context
) {
    private val dataFile: File

    init {
        val cacheDir = context.filesDir
        if (!cacheDir.exists()) cacheDir.mkdirs()

        dataFile = File(cacheDir, "secureData")
        try {
            dataFile.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun available(): Boolean {
        return dataFile.exists()
    }

    fun store(securePassword: String, deskey: String, vcode: Long) {
        val content = JSONObject()
        content.put("k", deskey)
        content.put("v", vcode)

        val imei = context.getAndroidId()

        val stream = FileOutputStream(dataFile)
        val encrypt: String = Protocol.encFile(securePassword, imei, content.toString())
        stream.write(encrypt.toByteArray())

        stream.flush()
        stream.close()
    }

    fun load(securePassword: String): Boolean {
        try {
            val imei = context.getAndroidId()


            val stream = FileInputStream(dataFile)
            val buffer = ByteArray(1024)
            val read = stream.read(buffer)
            stream.close()
            val content = ByteArray(read)
            System.arraycopy(buffer, 0, content, 0, read)

            val data = String(content)
            val decrypt: String = Protocol.decFile(securePassword, imei, data)
            val json = JSONObject(decrypt)
            HamrahBankSendGprs.deskey = json.getInt("k")
            HamrahBankSendGprs.vCode = json.getInt("v")

            return true

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun delete(): Boolean {
        return try {
            dataFile.delete()
        } catch (_: Exception) {
            false
        }
    }
}

// temp
object HamrahBankSendGprs {

    var vCode: Int
        get() {
            return 0
        }
        set(value) {
            vCode = value
        }
    var deskey: Int
        get() {
            return 0
        }
        set(value) {
            deskey = value
        }
}

// temp
object Protocol {
    fun encFile(securePassword: String, imei: String, toString: String): String {
        return ""
    }

    fun decFile(securePassword: String, imei: String, data: String): String {
        return ""
    }

}
