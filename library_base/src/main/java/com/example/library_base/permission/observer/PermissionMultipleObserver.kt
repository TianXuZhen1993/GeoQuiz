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
 * 默认单个权限设置
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/6/22 17:38
 */
class PermissionMultipleObserver(private val permissions: Array<String>, private val function: () -> Unit) :
    DefaultLifecycleObserver {

    @StringRes
    var infoRes: Int = R.string.permission_refuse_info_default

    @StringRes
    var setRes: Int = R.string.permission_set_default

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    private val infoDialog: PermissionInfoDialog by lazy {
        PermissionInfoDialog.Builder().apply {
            content = CoreUtils.getApp().getString(infoRes)
        }.create()
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        when (owner) {
            is FragmentActivity -> {
                //权限申请
                permissionLauncher =
                    owner.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { maps ->
                        infoDialog.dismiss()
                        var isGranted = true
                        maps.forEach { (_, agree) ->
                            isGranted = isGranted and agree
                        }
                        if (isGranted) {
                            //权限全部同意，处理业务逻辑
                            function()
                        } else {
                            //权限没有同意，进入设置界面
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
        //权限没有开启，显示INFO信息
        infoDialog.show(activity.supportFragmentManager)
        if (::permissionLauncher.isInitialized) {
            permissionLauncher.launch(permissions)
        } else {
            throw NullPointerException("${this.javaClass.name}没有添加生命周期拥有者，检查lifecycle.addObserver")
        }
    }

}