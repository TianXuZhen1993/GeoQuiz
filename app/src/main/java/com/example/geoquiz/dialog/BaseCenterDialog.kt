package com.example.geoquiz.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.blankj.utilcode.util.ScreenUtils

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/5/7 14:43
 */
abstract class BaseCenterDialog : BaseDialogFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStyle()
    }


    /**
     * 设置统一样式
     */
    private fun setStyle() {
        dialog?.window?.apply {
            val layoutParams = attributes
            layoutParams.width = (ScreenUtils.getScreenWidth() * 0.8f).toInt()
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            layoutParams.gravity = Gravity.CENTER
            attributes = layoutParams
        }
    }
}