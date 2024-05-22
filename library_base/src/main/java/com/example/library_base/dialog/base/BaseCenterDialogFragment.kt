package com.example.library_base.dialog.base

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.blankj.utilcode.util.ScreenUtils
import com.example.library_base.R

/**
 *
 * 居中显示的DialogFragment 基类
 * @author TXZ
 * @version 1.0
 * created by 2024/5/7 14:43
 */
abstract class BaseCenterDialogFragment : BaseDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.base_center_dialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialogWH()
    }


    /**
     * 设置Dialog的宽高样式，默认是0.8的宽，自适应高
     */
    open fun setDialogWH() {
        //默认dialog宽度为屏幕的0.8 居中显示
        dialog?.window?.apply {
            val layoutParams = attributes
            layoutParams.width = (ScreenUtils.getScreenWidth() * 0.8f).toInt()
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            layoutParams.gravity = Gravity.CENTER
            attributes = layoutParams
        }
    }
}