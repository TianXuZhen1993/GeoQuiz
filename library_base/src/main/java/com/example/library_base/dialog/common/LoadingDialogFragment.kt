package com.example.library_base.dialog.common

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.blankj.utilcode.util.ScreenUtils
import com.example.library_base.databinding.DialogLoadingBinding
import com.example.library_base.dialog.base.BaseCenterDialogFragment

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/5/22 15:50
 */
class LoadingDialogFragment : BaseCenterDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DialogLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setDialogWH() {
        //默认dialog宽度为屏幕的0.8 居中显示
        dialog?.window?.apply {
            val layoutParams = attributes
            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            layoutParams.gravity = Gravity.CENTER
            attributes = layoutParams
        }
    }
}