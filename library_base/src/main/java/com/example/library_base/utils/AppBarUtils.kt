package com.example.library_base.utils

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
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
    val appStatusBarHeight by lazy {
        getStatusBarHeightByResource()
    }

    /**
     * 通过资源文件获取，存在相对风险
     *
     * @return
     */
    @SuppressLint("DiscouragedApi", "InternalInsetResource")
    fun getStatusBarHeightByResource(): Int {
        Resources.getSystem().apply {
            val resourcesId = getIdentifier("status_bar_height", "dimen", "android")
            return getDimensionPixelSize(resourcesId)
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

    /**
     * 设置全面屏
     *
     * @param window
     * @param conflictView
     */
    fun setFullScreen(window: Window, conflictView: View? = null) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        if (conflictView == null) return
        ViewCompat.setOnApplyWindowInsetsListener(conflictView) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            // 此处更改的 margin，也可设置 padding，视情况而定
            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.top
                leftMargin = insets.left
                bottomMargin = insets.bottom
                rightMargin = insets.right
            }
            WindowInsetsCompat.CONSUMED
        }
    }
}