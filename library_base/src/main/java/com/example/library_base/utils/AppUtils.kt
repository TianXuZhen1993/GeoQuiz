package com.example.library_base.utils

import android.content.pm.PackageManager

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/6/3 11:54
 */
object AppUtils {

    /**
     * 判断APP是否已经被安装
     * Return whether the app is installed.
     *
     * @param pkgName The name of the package.
     * @return true：yes   false ：no
     */
    fun isAppInstalled(pkgName: String): Boolean {
        if (pkgName.isEmpty()) return false
        return try {
            val pm = CoreUtils.getApp().packageManager
            pm.getApplicationInfo(pkgName, 0).enabled
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    /**
     * Return whether the application was first installed.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    fun isFirstTimeInstalled(): Boolean {
        try {
            val pi = CoreUtils.getApp().packageManager.getPackageInfo(CoreUtils.getApp().packageName, 0)
            return pi.firstInstallTime == pi.lastUpdateTime
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return true
        }
    }
}