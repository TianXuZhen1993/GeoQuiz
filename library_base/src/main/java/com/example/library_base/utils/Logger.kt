package com.example.library_base.utils

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

/**
 * Logger 工具类
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/5/11 22:46
 */
object Logger {
    /**
     * 初始化Logger
     * @param isLogger 是否开启日志
     */
    fun initLogUtils(isLogger: Boolean) {
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return isLogger
            }
        })
    }

    fun d(any: Any?) {
        Logger.d(any)
    }

    fun d(any: Any?, tag: String) {
        Logger.d(tag, any)
    }

    fun i() {

    }
}