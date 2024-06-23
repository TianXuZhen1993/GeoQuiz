package com.example.library_base.permission

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.library_base.R
import com.example.library_base.dialog.common.OneBtnInfoDialogFragment

/**
 * 权限设置
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/6/22 17:38
 */
class PermissionLauncher(private val permission: String, private val function: () -> Unit) :
    DefaultLifecycleObserver {

    private lateinit var permissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        when (owner) {
            is FragmentActivity -> {
                permissionLauncher = owner.registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                        if (granted) {
                            //处理业务逻辑
                            function()
                        } else {
                            if (owner.shouldShowRequestPermissionRationale(permission)) {
                                val showInfoDialog = OneBtnInfoDialogFragment().Builder().apply {
                                    content = owner.resources.getString(R.string.permission_show)
                                }.create()
                                showInfoDialog.show(owner.supportFragmentManager)
                            } else {
                                val settingDialog = SettingPermissionDialog.Builder().apply {
                                    content = owner.resources.getString(R.string.permission_setting)
                                }.create()
                                settingDialog.show(owner.supportFragmentManager)
                            }
                        }
                    }
            }

            else -> {
                throw NullPointerException("${this.javaClass.name}观察的生命周期不是Activity或者Fragment")
            }
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        permissionLauncher.unregister()
    }

    fun request() {
        if (::permissionLauncher.isInitialized) {
            permissionLauncher.launch(permission)
        }
    }
}