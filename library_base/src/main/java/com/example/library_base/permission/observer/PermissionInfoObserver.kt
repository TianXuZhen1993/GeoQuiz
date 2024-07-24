package com.example.library_base.permission.observer

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.library_base.R
import com.example.library_base.permission.dialog.PermissionInfoDialog
import com.example.library_base.permission.dialog.SettingPermissionDialog
import com.example.library_base.core.CoreUtils

/**
 * 请求权限的时候，同时显示请求信息 ，国内适配
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/6/23 13:28
 */
class PermissionInfoObserver(private val permission: String, private val function: () -> Unit) :
    DefaultLifecycleObserver {

    private lateinit var permissionLauncher: ActivityResultLauncher<String>

    @StringRes
    var infoRes: Int = R.string.permission_refuse_info_default

    @StringRes
    var setRes: Int = R.string.permission_set_default

    private val infoDialog: PermissionInfoDialog by lazy {
        PermissionInfoDialog.Builder().apply {
            content = CoreUtils.getApp().getString(infoRes)
        }.create()
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        when (owner) {
            is FragmentActivity -> {
                permissionLauncher = owner.registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                    infoDialog.dismiss()
                    if (granted) {
                        //处理业务逻辑
                        function()
                    } else {
                        val settingDialog = SettingPermissionDialog.Builder().apply {
                            content = owner.resources.getString(setRes)
                        }.create()
                        settingDialog.show(owner.supportFragmentManager)
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

    fun request(activity: FragmentActivity) {
        infoDialog.show(activity.supportFragmentManager)
        if (::permissionLauncher.isInitialized) {
            permissionLauncher.launch(permission)
        }
    }
}