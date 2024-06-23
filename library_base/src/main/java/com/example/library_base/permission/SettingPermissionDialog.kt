package com.example.library_base.permission

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.library_base.databinding.DialogSettingPermissionBinding
import com.example.library_base.dialog.base.BaseCenterDialogFragment


/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/6/23 0:42
 */
class SettingPermissionDialog : BaseCenterDialogFragment() {
    private var _builderConfig = Builder()
    private lateinit var _binding: DialogSettingPermissionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DialogSettingPermissionBinding.inflate(inflater, container, false)
        _binding.apply {
            tvTitle.text = _builderConfig.title
            tvContent.text = _builderConfig.content
            tvCancel.text = _builderConfig.tvCancel
            tvSure.text = _builderConfig.tvSure
            tvCancel.setOnClickListener {
                _builderConfig.onCancelClick()
                dismiss()
            }
            tvSure.setOnClickListener {
                goSetting()
                dismiss()
            }
        }
        return _binding.root
    }

    private fun goSetting() {
        //跳转应用消息，间接打开应用权限设置-效率高
        val intent = Intent()
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", requireContext().packageName, null)
        intent.setData(uri)
        startActivity(intent)
    }


    class Builder() {

        var title: String = ""

        var content: String = ""

        var tvCancel: String = "取消"

        var tvSure: String = "去设置"

        var onCancelClick: () -> Unit = {

        }

        fun create(): SettingPermissionDialog {
            return SettingPermissionDialog().apply {
                _builderConfig = this@Builder
            }
        }
    }
}