package com.example.geoquiz.utils

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.os.Build
import com.blankj.utilcode.util.ScreenUtils


/**
 * 获取屏幕相关参数
 */
object ScreenUtils {

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    fun getScreenWidth(): Int {
        return ScreenUtils.getScreenWidth()
    }

    /**
     * 获取屏幕宽高
     *
     * @param context
     * @return
     */
    fun getScreenMetrics(context: Context): Point {
        return Point().apply {
            x = context.resources.displayMetrics.widthPixels
            y = context.resources.displayMetrics.heightPixels
        }
    }

    fun getScreenMetrics(activity: Activity): Point {
        val point = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val bounds = activity.windowManager.currentWindowMetrics.bounds
            point.x = bounds.width()
            point.y = bounds.height()
        } else {
            activity.windowManager.defaultDisplay.getSize(point)
        }
        return point
    }
}