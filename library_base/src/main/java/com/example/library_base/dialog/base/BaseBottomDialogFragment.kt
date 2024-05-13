package com.example.library_base.dialog.base

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.blankj.utilcode.util.Utils

/**
 *
 * 位于底部显示的DialogFragment 基类
 * @author TXZ
 * @version 1.0
 * created by 2024/5/8 11:39
 */
abstract class BaseBottomDialogFragment : BaseDialogFragment() {
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