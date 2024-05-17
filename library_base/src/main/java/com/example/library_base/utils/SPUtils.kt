package com.example.library_base.utils

import android.content.SharedPreferences
import com.blankj.utilcode.util.SPUtils

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/5/16 18:26
 */
object SPUtils {
    //项目APP的基础设置
    private const val APP_CONFIG = "APP_CONFIG"

    private lateinit var sp: SharedPreferences

    /**
     * 返回SharedPreferences实例
     *
     * @return
     */
    fun getInstance(): SharedPreferences {
        return sp
    }

    fun put(key: String, value: String) {
        val edit = sp.edit()
        edit.apply()
    }
}