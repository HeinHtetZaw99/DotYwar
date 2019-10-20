package com.daniel.appbase.components

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build

import androidx.core.app.ActivityCompat

import android.content.Context.MODE_PRIVATE

object PermissionUtil {
    const val PERMISSION_REQUEST_READ_CONTACTS = 11
    const val PERMISSION_REQUEST_CALL_PHONE = 12
    const val PERMISSION_REQUEST_READ_STORAGE = 13
    const val PERMISSION_REQUEST_LOCATION = 14
    const val PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 15

    private const val PREFS_FILE_NAME = "TH_PERMISSION_LIST"

    /*
     * Check if version is marshmallow and above.
     * Used in deciding to ask runtime permission
     * */
    private fun shouldAskPermission(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    private fun shouldAskPermission(context: Context, permission: String): Boolean {
        if (shouldAskPermission()) {
            val permissionResult = ActivityCompat.checkSelfPermission(context, permission)
            return permissionResult != PackageManager.PERMISSION_GRANTED
        }
        return false
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun checkPermission(activity: Activity, permission: String, listener: PermissionAskListener) {
        /*
         * If permission is not granted
         * */
        if (shouldAskPermission(activity, permission)) {
            /*
             * If permission denied previously
             * */
            if (activity.shouldShowRequestPermissionRationale(permission)) {
                listener.onPermissionPreviouslyDenied()
            } else {
                /*
                 * Permission denied or first time requested
                 * */
                if (isFirstTimeAskingPermission(activity, permission)) {
                    firstTimeAskingPermission(activity, permission, false)
                    listener.onNeedPermission()
                } else {
                    /*
                     * Handle the feature without permission or ask user to manually allow permission
                     * */
                    listener.onPermissionDisabled()
                }
            }
        } else {
            listener.onPermissionGranted()
        }
    }


    private fun firstTimeAskingPermission(
        context: Context,
        permission: String,
        isFirstTime: Boolean
    ) {
        val sharedPreference = context.getSharedPreferences(PREFS_FILE_NAME, MODE_PRIVATE)
        sharedPreference.edit().putBoolean(permission, isFirstTime).apply()
    }

    private fun isFirstTimeAskingPermission(context: Context, permission: String): Boolean {
        return context.getSharedPreferences(PREFS_FILE_NAME, MODE_PRIVATE)
            .getBoolean(permission, true)
    }


    /*
     * Callback on various cases on checking permission
     *
     * 1.  Below M, runtime permission not needed. In that case onPermissionGranted() would be called.
     *     If permission is already granted, onPermissionGranted() would be called.
     *
     * 2.  Above M, if the permission is being asked first time onNeedPermission() would be called.
     *
     * 3.  Above M, if the permission is previously asked but not granted, onPermissionPreviouslyDenied()
     *     would be called.
     *
     * 4.  Above M, if the permission is disabled by device policy or the user checked "Never ask again"
     *     check box on previous request permission, onPermissionDisabled() would be called.
     * */
    interface PermissionAskListener {
        /*
         * Callback to ask permission
         * */
        fun onNeedPermission()

        /*
         * Callback on permission denied
         * */
        fun onPermissionPreviouslyDenied()

        /*
         * Callback on permission "Never show again" checked and denied
         * */
        fun onPermissionDisabled()

        /*
         * Callback on permission granted
         * */
        fun onPermissionGranted()
    }
}
