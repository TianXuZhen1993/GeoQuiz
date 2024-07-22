package com.example.library_base.utils

import android.os.Build
import android.util.Log
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * 进行错误信息处理与保存，保存日期30天，存放路径为 外部私有目录下的，crash文件夹中
 * @version 1.0
 * @date 2024/7/22 10:17
 */
object CrashHandlerUtils : Thread.UncaughtExceptionHandler {
    private const val TAG = "CrashHandlerUtils"
    private const val CRASH = "crash"
    private val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
    private val fileFormat = SimpleDateFormat("yyyyMMdd", Locale.CHINA)
    private val timeFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA)

    fun init() {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(thread: Thread, e: Throwable) {
        val filename = generateFileName()
        val file = File(CoreUtils.getApp().getExternalFilesDir(CRASH), filename)
        //如果crash文件不存在，就创建crash 文件夹，无需要FileProvider进行分享
        if (!file.parentFile!!.exists()) file.mkdir()
        val crashInfo = createCrash(thread, e)

    }

    private fun createCrash(thread: Thread, e: Throwable): String {
        val sb = StringBuilder()
        sb.appendLine("错误时间： ${timeFormat.format(System.currentTimeMillis())}")
        sb.appendLine("设备信息：${Build.BRAND}")
        sb.appendLine("设备型号：${Build.MODEL}")
        sb.appendLine("品牌名称：${Build.PRODUCT}")
        sb.appendLine("SDK版本：${Build.VERSION.SDK_INT}")
        sb.appendLine("Android版本：${Build.VERSION.RELEASE}")
        sb.appendLine("----------------------")
        sb.appendLine("错误线程Thread：${thread.name}")
        sb.appendLine("错误线程：${e.message}")
        sb.appendLine("错误堆栈：${e.stackTraceToString()}")
        return sb.toString()
    }

    private fun generateFileName(): String {
        return "crash-${fileFormat.format(System.currentTimeMillis())}.txt"
    }
}

