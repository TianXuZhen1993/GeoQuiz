package com.example.geoquiz.utils

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import org.junit.Test


/**
 *
 * 时间类的转换工具
 * @author TXZ
 * @version 1.0
 * created by 2024/3/26 16:41
 */
class TimeUtils {
    companion object {
        /**
         * 用于保存在不同线程中的SimpleDateFormat，而不用重复创建
         */
        @JvmStatic
        private val SDF_THREAD_LOCAL = object : ThreadLocal<Map<String, SimpleDateFormat>>() {
            override fun initialValue(): Map<String, SimpleDateFormat> {
                return mutableMapOf()
            }
        }

        @SuppressLint("SimpleDateFormat")
        fun getSafeSimpleDateFormat(pattern: String): SimpleDateFormat {
            val sdfMap = SDF_THREAD_LOCAL.get()!!.toMutableMap()
            var simpleDateFormat = sdfMap[pattern]
            if (simpleDateFormat == null) {
                simpleDateFormat = SimpleDateFormat(pattern)
                sdfMap[pattern] = simpleDateFormat
            }
            return simpleDateFormat
        }
    }
}

