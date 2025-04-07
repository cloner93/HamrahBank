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