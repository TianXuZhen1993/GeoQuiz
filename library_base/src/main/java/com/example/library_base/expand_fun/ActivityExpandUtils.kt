package com.example.library_base.expand_fun

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams


/**
 * 设置全面屏，保留状态栏
 *
 * @param window
 * @param conflictView
 */
fun AppCompatActivity.setFunScreen(window: Window, conflictView: View? = null) {
    //取消状态栏的间隔
    WindowCompat.setDecorFitsSystemWindows(window, false)
    //设置成透明颜色
    window.statusBarColor = Color.TRANSPARENT
    window.navigationBarColor = Color.TRANSPARENT
    if (conflictView == null) return
//    conflictView.setOnApplyWindowInsetsListener { v, insets ->
//        insets.systemWindowInsetTop
//        insets
//    }
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