package com.example.geoquiz.utils

import android.content.res.Resources
import android.os.Build
import android.util.Log
import android.util.TypedValue


object SizeUtils {

    private const val TAG = "SizeUtils"


    /**
     * dp转px
     *
     * @param dp 传入的dp
     * @return
     * @see com.blankj.utilcode.util.SizeUtils.dp2px 同方法可以参考
     */
    fun dp2px(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics)
    }

    /**
     * px转dp，同方法可以参考
     *
     * @param pxValue 传入的dp
     * @return
     * @see com.blankj.utilcode.util.SizeUtils.px2dp 同方法可以参考
     */
    fun px2dp(pxValue: Float): Float {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            TypedValue.deriveDimension(TypedValue.COMPLEX_UNIT_DIP, pxValue, Resources.getSystem().displayMetrics)
        } else {
            val scale = Resources.getSystem().displayMetrics.density
            (pxValue / scale + 0.5f).toInt().toFloat()
        }
    }

    /**
     * sp转px，同方法可以参考
     *
     * @param sp 传入的dp
     * @return
     * @see com.blankj.utilcode.util.SizeUtils.sp2px
     */
    fun sp2px(sp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().displayMetrics)
    }

    /**
     * px转sp，同方法可以参考
     *
     * @param pxValue 传入的dp
     * @return
     * @see com.blankj.utilcode.util.SizeUtils.px2sp
     */
    fun px2sp(pxValue: Float): Float {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            TypedValue.deriveDimension(TypedValue.COMPLEX_UNIT_SP, pxValue, Resources.getSystem().displayMetrics)
        } else {
            val fontScale = Resources.getSystem().displayMetrics.scaledDensity
            (pxValue / fontScale + 0.5f).toInt().toFloat()
        }
    }


    fun logScreenSize() {
        Resources.getSystem().displayMetrics.apply {
            Log.i(
                TAG, "屏幕宽度px：${widthPixels} \n" +
                        "屏幕高度px：${heightPixels} \n" +
                        "屏幕density：${density} \n" +
                        "屏幕scaledDensity:${scaledDensity}"
            )
        }
    }

}
