package com.example.geoquiz.dialog

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

private const val TAG = "BaseDialogFragment"

/**
 *
 *  基础Dialog的弹框
 * @author TXZ
 * @version 1.0
 * created by 2024/4/26 9:56
 */
abstract class BaseDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

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


    fun show(superFragmentManager: FragmentManager) {
        show(superFragmentManager, javaClass.simpleName)
    }
}