package com.tian.utilsproject.utils

import android.content.Context

object PxUtils{
    fun dp2px(context: Context, dp: Int): Float {
        val density = context.resources.displayMetrics.density
        return (dp * density + 0.5f)
    }

    fun dp2px(context: Context, dp: Float): Float {
        val density = context.resources.displayMetrics.density
        return (dp * density + 0.5f)
    }
}
