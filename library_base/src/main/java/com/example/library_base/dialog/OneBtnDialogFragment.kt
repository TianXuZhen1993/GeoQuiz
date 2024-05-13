package com.example.library_base.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.library_base.databinding.DialogOneBtnBinding
import com.example.library_base.dialog.base.BaseCenterDialogFragment


/**
 *
 * 只有一个按钮的提示dialog，集成BaseCenterDialog的圆角 r=10dp
 * @author TXZ
 * @version 1.0
 * created by 2024/5/13 15:37
 */
class OneBtnDialogFragment : BaseCenterDialogFragment() {
    private var builderConfig = Builder()

    //不同项目的oneBtn应该是不一样的，根据设计稿修改一些view的大小跟颜色即可
    private lateinit var binding: DialogOneBtnBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogOneBtnBinding.inflate(inflater, container, false)
        binding.apply {
            tvTitle.text = builderConfig.title
            tvContent.text = builderConfig.content
            btn.text = builderConfig.btnText
            btn.setOnClickListener {
                builderConfig.onBtnClick
                dismiss()
            }
        }
        return binding.root
    }


    fun setContent(content: String) {
        if (::binding.isInitialized) {
            binding.tvContent.text = content
        } else {
            builderConfig.content = content
        }
    }


    fun setBuilder(builder: Builder) {
        builderConfig = builder
    }

    inner class Builder() {
        var title = "提示"
        var content: String = "文本内容"
        var btnText: String = "确认"
        //不能直接在Build里面设置dismiss，因为fragmentManager 还没初始化
        var onBtnClick: () -> Unit = {

        }

        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun setContent(content: String): Builder {
            this.content = content
            return this
        }

        fun setBtnText(btnText: String): Builder {
            this.btnText = btnText
            return this
        }

        fun setOnClickListener(onLick: () -> Unit): Builder {
            onBtnClick = onLick
            return this
        }

        fun create(): OneBtnDialogFragment {
            return OneBtnDialogFragment().apply {
                setBuilder(this@Builder)
            }
        }
    }


}