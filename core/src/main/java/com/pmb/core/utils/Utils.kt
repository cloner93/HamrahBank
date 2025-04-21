package com.pmb.core.utils

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun Context.openApp(packageName: String,url: String) {
    val launchIntent = this.packageManager.getLaunchIntentForPackage(packageName)
    if (launchIntent != null) {
        startActivity(launchIntent)
    } else {
        openWebPage(url)
    }
}
fun Context.openWebPage(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
    startActivity(intent)
}
fun Context.shareText(sharingText: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "textToShare") // TODO:: get receipt text
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}