package com.example.library_base.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

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

    private var sp: SharedPreferences = CoreUtils.application.getSharedPreferences(APP_CONFIG, Context.MODE_PRIVATE)


    private var isChangeName = true

    fun getDefaultSP(): SharedPreferences = sp

    /**
     * 返回SharedPreferences实例
     *
     * @return
     */
    fun getInstance(name: String = ""): SharedPreferences {
        val spName = name.ifEmpty { APP_CONFIG }
        return CoreUtils.application.getSharedPreferences(spName, Context.MODE_PRIVATE)
    }

    fun putString(key: String, value: String) {
        sp.edit().putString(key, value).apply()
    }

    fun putInt(key: String, value: Int) {
        sp.edit().putInt(key, value).apply()
    }

    fun putBoolean(key: String, value: Boolean) {
        sp.edit().putBoolean(key, value).apply()
    }

    fun putLong(key: String, value: Long) {
        sp.edit().putLong(key, value).apply()
    }
}