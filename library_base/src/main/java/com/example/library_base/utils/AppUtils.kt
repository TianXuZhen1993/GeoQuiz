package com.example.library_base.utils

import android.content.pm.PackageManager
import android.os.Build
import com.example.library_base.core.CoreUtils
import com.example.library_base.utils.AppUtils.getAppVersionCode
import com.example.library_base.utils.AppUtils.getAppVersionName
import com.example.library_base.utils.AppUtils.isAppInstalled
import com.example.library_base.utils.AppUtils.isFirstTimeInstalled

/**
 *
 * APP应用工具类
 * @author TXZ
 * @version 1.0
 * created by 2024/6/3 11:54
 *
 * @see isAppInstalled 判断包名的app是否安装
 * @see isFirstTimeInstalled 是否第一次安装
 * @see getAppVersionName 获取版本名称
 * @see getAppVersionCode 获取版本代码
 *
 */
object AppUtils {

    /**
     * 判断APP是否已经被安装
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


    /**
     * Return whether the application was first installed.
     * 在华为鸿蒙上可能失效
     * @return `true`: yes<br></br>`false`: no
     */
    fun isFirstTimeInstalled(): Boolean {
        try {
            val pi =
                CoreUtils.getApp().packageManager.getPackageInfo(CoreUtils.getApp().packageName, 0)
            return pi.firstInstallTime == pi.lastUpdateTime
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return true
        }
    }


    /**
     * 获取版本名称
     * @return the application's version name
     */
    fun getAppVersionName(): String {
        try {
            val content = CoreUtils.getApp()
            val pm = content.packageManager
            val pi = pm.getPackageInfo(content.packageName, 0)
            return if (pi == null) "" else pi.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return ""
        }
    }

    /**
     * 获取版本代码
     * @return the application's version name
     */
    fun getAppVersionCode(): Int {
        try {
            val content = CoreUtils.getApp()
            val pm = content.packageManager
            val pi = pm.getPackageInfo(content.packageName, 0)
            return if (pi == null) {
                -1
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    pi.longVersionCode.toInt()
                } else {
                    pi.versionCode
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return -1
        }
    }
}