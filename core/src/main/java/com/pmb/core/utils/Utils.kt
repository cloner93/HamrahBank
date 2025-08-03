package com.pmb.core.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.provider.Settings
import androidx.core.net.toUri

fun Context.openApp(packageName: String, url: String) {
    val launchIntent = this.packageManager.getLaunchIntentForPackage(packageName)
    if (launchIntent != null) {
        startActivity(launchIntent)
    } else {
        openWebPage(url)
    }
}

fun Context.actionCall(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = "tel:$phoneNumber".toUri()
    }
    startActivity(intent)
}

fun Context.openWebPage(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
    startActivity(intent)
}

fun Context.shareText(sharingText: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, sharingText)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

fun Context.fetchContactPhoneNumber(contactUri: Uri?): String? {
    return contactUri?.let {


        val cursor = contentResolver.query(
            contactUri,
            arrayOf(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME),
            null,
            null,
            null
        )

        cursor?.use { c ->
            if (c.moveToFirst()) {
                val idIndex = c.getColumnIndex(ContactsContract.Contacts._ID)
                c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)

                val contactId = c.getString(idIndex)

                // Step 2: Now query phone number with Contact ID
                val phonesCursor = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
                    "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?",
                    arrayOf(contactId),
                    null
                )

                phonesCursor?.use { phoneC ->
                    if (phoneC.moveToFirst()) {
                        val phoneNumberIndex =
                            phoneC.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        val phoneNumber = phoneC.getString(phoneNumberIndex)

                        return phoneNumber
                    }
                }
            }
        }

        return null
    } ?: run {
        null
    }
}

fun Context.copyToClipboard(text: String) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("Deposit Info", text)
    clipboard.setPrimaryClip(clip)
}

fun Context.getAndroidId(): String =
    Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)

