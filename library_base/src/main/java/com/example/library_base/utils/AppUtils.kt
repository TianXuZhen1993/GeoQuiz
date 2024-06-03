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
     * @return true：yes  false ：no
     */
    fun isAppInstalled(pkgName: String): Boolean {
        if (pkgName.isEmpty()) return false
        val pm = CoreUtils.getApp().packageManager
        return try {
            pm.getApplicationInfo(pkgName, 0).enabled
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}