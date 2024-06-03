package com.example.library_base.utils

import android.annotation.SuppressLint
import android.app.Application

/**
 *
 * 核心工具类，获取项目Application实例
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/5/17 9:27
 */
object CoreUtils {
    //        val app = getApplicationByReflect() ?: throw NullPointerException("reflect application failed")
    private val application: Application = UtilsContentProvider.application

    fun getApp(): Application {
        return application
    }

    /**
     * 通过反射获取Application实例
     *
     * @return
     */
    @SuppressLint("PrivateApi")
    private fun getApplicationByReflect(): Application? {
        try {
            val activityThreadClassName = "android.app.ActivityThread"
            val clazz = Class.forName(activityThreadClassName)
            val currentActivityThread = clazz.getDeclaredField("sCurrentActivityThread").apply {
                isAccessible = true
            }.get(null)
            val any = clazz.getMethod("getApplication").invoke(currentActivityThread)
            if (any is Application) return any
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}