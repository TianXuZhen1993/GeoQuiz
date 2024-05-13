package com.example.library_base.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.library_base.databinding.DialogInformBinding
import com.example.library_base.dialog.base.BaseCenterDialogFragment
import java.io.Serializable

/**
 *
 * 通知类的弹框
 * 包含一个标题，内容，确认
 * @author TXZ
 * @version 1.0
 * created by 2024/5/7 13:16
 */
class InformDialog : BaseCenterDialogFragment() {
    //dialog 的基本参数
    private var _build = Build()
    private lateinit var _informBinding: DialogInformBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _informBinding = DialogInformBinding.inflate(inflater, container, false)
        if (_build.content.isEmpty()) {
            throw Exception("InformDialog 构建错误，缺少content主体内容")
        }
        return _informBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _informBinding.apply {
            title.text = _build.title
            content.text = _build.content
            sure.text = _build.sure
        }
    }


    fun setBuild(build: Build) {
        _build = build
    }

    fun setContent(contentText: String) {
        _build.setContent(contentText)
    }

    class Build() : Serializable {
        var title: String = "系统提示"
        var content: String = ""
        var sure: String = "确定"

        fun setTitle(titleText: String): Build {
            title = titleText
            return this
        }

        fun setContent(contentText: String): Build {
            content = contentText
            return this
        }

        fun setSureText(sureText: String): Build {
            sure = sureText
            return this
        }

        fun create(): InformDialog {
            return InformDialog().apply {
                _build = this@Build
            }
        }
    }
}