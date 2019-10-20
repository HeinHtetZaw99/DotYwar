package com.daniel.appbase

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log

fun showLogD(text: String) {
    Log.d("APP_TAG", text)
}

fun showLogD(text: String, customTag: String) {
    Log.d(customTag, text)
}

fun showLogE(text: String) {
    Log.e("APP_TAG", text)
}

fun showLogE(text: String, customTag: String) {
    Log.e(customTag, text)
}

/**launching to the app settings in case of user has denied necessary permissions */
fun goToAppSettings(context: Context) {
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", context.packageName, null)
    )
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
}



