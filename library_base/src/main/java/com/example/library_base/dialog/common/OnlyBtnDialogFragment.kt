package com.example.library_base.dialog.common

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.library_base.databinding.DialogOnlyBtnBinding
import com.example.library_base.dialog.base.BaseCenterDialogFragment

/**
 *
 *  Dialog：删除图片
 * @author TXZ
 * @version 1.0
 * created by 2024/5/10 14:58
 */
class OnlyBtnDialogFragment : BaseCenterDialogFragment() {
    private var _builderConfig = Builder()
    private lateinit var binding: DialogOnlyBtnBinding
    lateinit var onClick: View.OnClickListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogOnlyBtnBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStyle()
        binding.btn.text = _builderConfig.btnText
        if (::onClick.isInitialized) {
            binding.btn.setOnClickListener(onClick)
        } else {
            binding.btn.setOnClickListener {
                _builderConfig.onBtnClick()
                dismiss()
            }
        }
    }


    fun setBtnText(text: String) {
        if (::binding.isInitialized) {
            binding.btn.text = text
        } else {
            _builderConfig.btnText = text
        }
    }


    /**
     * 设置统一样式
     */
    private fun setStyle() {
        dialog?.window?.apply {
            val layoutParams = attributes
            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            layoutParams.gravity = Gravity.CENTER
            attributes = layoutParams
        }
    }


    inner class Builder() {

        var btnText: String = ""

        //不能直接在Build里面设置dismiss，因为fragmentManager 还没初始化
        var onBtnClick: () -> Unit = {

        }

        fun setBtnText(content: String): Builder {
            this.btnText = content
            return this
        }


        fun setOnClickListener(onLick: () -> Unit): Builder {
            onBtnClick = onLick
            return this
        }

        fun create(): OnlyBtnDialogFragment {
            return this@OnlyBtnDialogFragment.apply {
                _builderConfig = this@Builder
            }
        }
    }
}