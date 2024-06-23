package com.example.library_base.permission

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.library_base.R
import com.example.library_base.dialog.common.OneBtnInfoDialogFragment

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/6/23 13:28
 */
class PermissionInfoLauncher(private val permission: String, private val function: () -> Unit) :
    DefaultLifecycleObserver {

    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private lateinit var fragmentManager: FragmentManager
    private val infoDialog: PermissionInfoDialog by lazy {
        PermissionInfoDialog.Builder().apply {

        }.create()
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        when (owner) {
            is FragmentActivity -> {
                fragmentManager = owner.supportFragmentManager
                permissionLauncher =
                    owner.registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                        infoDialog.dismiss()
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
        if (::fragmentManager.isInitialized) {
            infoDialog.show(fragmentManager)
        }
        if (::permissionLauncher.isInitialized) {
            permissionLauncher.launch(permission)
        }
    }
}