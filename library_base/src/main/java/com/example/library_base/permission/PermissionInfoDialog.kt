package com.example.library_base.permission

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.library_base.databinding.DialogPermissionInfoBinding
import com.example.library_base.dialog.base.BaseTopDialogFragment
import com.example.library_base.expand_fun.toast

/**
 * 显示权限的提示内容
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/6/23 1:27
 */
class PermissionInfoDialog : BaseTopDialogFragment() {

    private var _builderConfig = Builder()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = DialogPermissionInfoBinding.inflate(inflater, container, false)
        binding.apply {
            if (_builderConfig.title.isNotEmpty()) tvTitle.text = _builderConfig.title
            tvTitle.visibility = if (_builderConfig.isTitleVisible) View.VISIBLE else View.GONE
            if (_builderConfig.content.isNotEmpty()) tvContent.text = _builderConfig.content
        }
        return binding.root
    }

    class Builder() {
        var title: String = ""
        var isTitleVisible: Boolean = true
        var content: String = ""
        fun create(): PermissionInfoDialog {
            return PermissionInfoDialog().apply {
                _builderConfig = this@Builder
            }
        }
    }
}