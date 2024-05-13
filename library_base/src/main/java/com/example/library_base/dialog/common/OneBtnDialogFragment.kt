package com.example.library_base.dialog.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.example.library_base.databinding.DialogOneBtnBinding
import com.example.library_base.dialog.base.BaseCenterDialogFragment


/**
 *
 * 只有一个按钮的提示dialog，集成BaseCenterDialog的圆角 r=8dp
 * @author TXZ
 * @version 1.0
 * created by 2024/5/13 15:37
 */
class OneBtnDialogFragment : BaseCenterDialogFragment() {
    private var _builderConfig = Builder()

    //不同项目的oneBtn应该是不一样的，根据设计稿修改一些view的大小跟颜色即可
    private lateinit var binding: DialogOneBtnBinding

    private lateinit var onClick: OnClickListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogOneBtnBinding.inflate(inflater, container, false)
        if (_builderConfig.content.isEmpty()) {
            throw Exception("OneBtnDialogFragment 构建错误，缺少content主体内容")
        }
        //绑定参数
        binding.apply {
            tvTitle.text = _builderConfig.title
            tvContent.text = _builderConfig.content
            btn.text = _builderConfig.btnText
            //如果build 跟 dialog同时实现，则优先选择dialog
            if (::onClick.isInitialized) {
                btn.setOnClickListener(onClick)
            } else {
                btn.setOnClickListener {
                    _builderConfig.onBtnClick
                    dismiss()
                }
            }
        }
        return binding.root
    }


    fun setContent(content: String) {
        if (::binding.isInitialized) {
            binding.tvContent.text = content
        } else {
            _builderConfig.content = content
        }
    }


    fun setBtnOnClickListener(onClick: OnClickListener) {
        if (::binding.isInitialized) {
            binding.btn.setOnClickListener(onClick)
        } else {
            this.onClick = onClick
        }
    }

    fun setBuilder(builder: Builder) {
        _builderConfig = builder
    }

    inner class Builder() {
        var title = "提示"
        var content: String = ""
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