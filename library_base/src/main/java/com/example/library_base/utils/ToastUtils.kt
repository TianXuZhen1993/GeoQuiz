package com.example.library_base.utils

import android.widget.Toast
import androidx.annotation.StringRes

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/5/17 11:37
 */
object ToastUtils {
    private val content = CoreUtils.getApp()

    fun showShort(text: CharSequence) {
        Toast.makeText(content, text, Toast.LENGTH_SHORT).show()
    }

    fun showShort(@StringRes strRes: Int) {
        Toast.makeText(content, strRes, Toast.LENGTH_SHORT).show()
    }

    fun showLong(text: CharSequence) {
        Toast.makeText(content, text, Toast.LENGTH_LONG).show()
    }

    fun showLong(@StringRes strRes: Int) {
        Toast.makeText(content, strRes, Toast.LENGTH_LONG).show()
    }
}