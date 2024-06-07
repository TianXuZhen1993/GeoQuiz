package com.example.library_base.utils

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams

/**
 *
 *  手机状态栏
 * @author TXZ
 * @version 1.0
 * created by 2024/6/5 11:51
 */
object AppBarUtils {
    val appStatusBarHeight = getStatusBarHeightByResource()

    /**
     * 通过资源文件获取，存在相对风险
     *
     * @return
     */
    @SuppressLint("DiscouragedApi", "InternalInsetResource")
    fun getStatusBarHeightByResource(): Int {
        Resources.getSystem().apply {
            val resourcesId = getIdentifier("status_bar_height", "dimen", "android")
            return if (resourcesId > 0) getDimensionPixelSize(resourcesId) else {
                Log.e("AppBarUtils", "警告：getStatusBarHeightByResource: not find dimen status_bar_height", )
                0
            }
        }
    }

    /**
     * 待界面加载之后才能获取
     *
     * @param window
     * @return
     */
    fun getStatusBarHeight(window: Window): Int {
        ViewCompat.getRootWindowInsets(window.decorView)?.apply {
            return getInsets(WindowInsetsCompat.Type.statusBars()).top
        }
        return -1
    }
}