package com.example.geoquiz.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.blankj.utilcode.util.ApiUtils
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.Utils

/**
 * @author TXZ
 * @version 1.0
 * created by 2024/5/8 11:39
 */
abstract class BaseBottomDialog : BaseDialogFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStyle()
        Utils.getApp()
    }


    /**
     * 设置统一样式
     */
    private fun setStyle() {
        dialog?.window?.apply {
            val layoutParams = attributes
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            layoutParams.gravity = Gravity.BOTTOM
            attributes = layoutParams
        }
    }
}