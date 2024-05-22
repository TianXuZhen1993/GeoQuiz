package com.example.library_base.dialog.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

/**
 *
 *  基础Dialog的弹框
 * @author TXZ
 * @version 1.0
 * created by 2024/4/26 9:56
 */
abstract class BaseDialogFragment : DialogFragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    /**
     * 设置弹框是否可以取消
     * @param isCancel
     */
    fun setDialogCancel(isCancel: Boolean) {
        isCancelable = isCancel
        dialog?.setCanceledOnTouchOutside(isCancel)
    }


    fun show(fm: FragmentManager) {
        show(fm, javaClass.simpleName)
    }
}