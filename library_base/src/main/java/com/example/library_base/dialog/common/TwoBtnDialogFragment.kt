package com.example.library_base.dialog.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.library_base.databinding.DialogTwoBtnBinding
import com.example.library_base.dialog.base.BaseCenterDialogFragment

/**
 *
 * 两个按钮的常用弹框
 * @author TXZ
 * @version 1.0
 * created by 2024/6/4 11:52
 */
class TwoBtnDialogFragment() : BaseCenterDialogFragment() {
    private var _builderConfig = Builder()
    private lateinit var _binding: DialogTwoBtnBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogTwoBtnBinding.inflate(inflater, container, false)
        _binding.apply {
            tvTitle.text = _builderConfig.tvTitle
            tvContent.text = _builderConfig.tvContent
            tvCancel.text = _builderConfig.tvCancel
            tvSure.text = _builderConfig.tvSure
            tvCancel.setOnClickListener {
                _builderConfig.onCancelClick()
                dismiss()
            }
            tvSure.setOnClickListener {
                _builderConfig.onSureClick()
                dismiss()
            }
        }
        return _binding.root
    }


    inner class Builder() {

        var tvTitle: String = ""

        var tvContent: String = ""

        var tvCancel: String = ""

        var tvSure: String = ""

        var onCancelClick: () -> Unit = {

        }

        //不能直接在Build里面设置dismiss，因为fragmentManager 还没初始化
        var onSureClick: () -> Unit = {
        }


        fun setTitle(title: String): Builder {
            this.tvTitle = title
            return this
        }

        fun setContent(title: String): Builder {
            this.tvContent = title
            return this
        }

        fun setCancelText(text: String): Builder {
            this.tvCancel = text
            return this
        }

        fun setSureText(text: String): Builder {
            tvSure = text
            return this
        }

        fun setSureClickListener(onSure: () -> Unit): Builder {
            onSureClick = onSure
            return this
        }

        fun setCancelClickListener(onCancel: () -> Unit): Builder {
            onCancelClick = onCancel
            return this
        }


        fun create(): TwoBtnDialogFragment {
            return this@TwoBtnDialogFragment.apply {
                _builderConfig = this@Builder
            }
        }
    }
}