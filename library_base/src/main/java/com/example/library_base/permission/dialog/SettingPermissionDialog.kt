package com.example.library_base.permission.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import com.example.library_base.databinding.DialogSettingPermissionBinding
import com.example.library_base.dialog.base.BaseCenterDialogFragment
import com.example.library_base.utils.IntentUtils


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
            tvSetting.text = _builderConfig.tvSure
            tvCancel.setOnClickListener {
                _builderConfig.onCancelClick()
                dismiss()
            }
            tvSetting.setOnClickListener {
                goSetting()
                dismiss()
            }
        }
        return _binding.root
    }

    private fun goSetting() {
        //跳转应用消息，间接打开应用权限设置-效率高
        val intent = IntentUtils.getPermissionSettingIntent(requireContext().packageName)
        if (_builderConfig.launcher != null) {
            _builderConfig.launcher?.launch(intent)
        } else {
            startActivity(intent)
        }
    }


    class Builder {

        var title: String = ""

        var content: String = ""

        var tvCancel: String = "取消"

        var tvSure: String = "去设置"

        var onCancelClick: () -> Unit = {

        }

        var launcher: ActivityResultLauncher<Intent>? = null

        fun create(): SettingPermissionDialog {
            return SettingPermissionDialog().apply {
                _builderConfig = this@Builder
            }
        }
    }
}