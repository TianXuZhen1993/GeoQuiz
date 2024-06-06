package com.example.library_base.utils

import android.util.Log
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy


/**
 * Logger 工具类
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/5/11 22:46
 */
object Logger {
    private var isLogger = false
    private const val _TOP = "------------------------------------------------------------------"
    private const val _BOTTOM = "------------------------------------------------------------------"

    /**
     * 初始化Logger
     * @param isLogger 是否开启日志
     */
    fun initLogUtils(isLogger: Boolean) {
        this.isLogger = isLogger
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true) // (Optional) Whether to show thread info or not. Default true
            .methodCount(2) // (Optional) How many method line to show. Default 2
            .methodOffset(1) // (Optional) Hides internal method calls up to offset. Default 5
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return isLogger
            }
        })
    }

    fun d(any: Any?) {
        Logger.d(any)
    }

    fun d(tag: String, any: Any?) {
        Logger.t(tag).d(any)
    }

    fun onlyLog(message: String, tag: String = "") {
        if (isLogger) {
            Log.d(tag, _TOP)
            Log.d(tag, message)
            Log.d(tag, _BOTTOM)
        }
    }
}

