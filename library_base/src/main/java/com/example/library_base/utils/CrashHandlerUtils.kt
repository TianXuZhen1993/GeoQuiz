package com.example.library_base.utils

import android.util.Log

/**
 * 进行错误信息处理与保存，保存日期30天，存放路径为 外部私有目录下的，crash文件夹中
 * @version 1.0
 * @date 2024/7/22 10:17
 */
object CrashHandlerUtils : Thread.UncaughtExceptionHandler {
    private val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()

    fun init() {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(thread: Thread, e: Throwable) {
        Log.d(TAG, "uncaughtException: " + thread.name)
        Log.d(TAG, "uncaughtException: " + e.message)
        Log.d(TAG, "uncaughtException: " + e.stackTraceToString())
    }

}

private const val TAG = "CrashHandlerUtils"