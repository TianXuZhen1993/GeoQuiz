package com.example.library_base.permission

import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.library_base.R
import com.example.library_base.dialog.common.OneBtnInfoDialogFragment
import com.example.library_base.utils.CoreUtils

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/6/23 13:48
 */
class PermissionInfoSetBackLauncher(
    private val permission: String,
    private val function: () -> Unit
) : DefaultLifecycleObserver {

    @StringRes
    var infoRes: Int = R.string.permission_info

    @StringRes
    var showRes: Int = R.string.permission_show

    @StringRes
    var setRes: Int = R.string.permission_setting

    private lateinit var permissionLauncher: ActivityResultLauncher<String>

    private val infoDialog: PermissionInfoDialog by lazy {
        PermissionInfoDialog.Builder().apply {
            content = CoreUtils.getApp().getString(infoRes)
        }.create()
    }

    private lateinit var setLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        when (owner) {
            is FragmentActivity -> {
                setLauncher = owner.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                        //设置界面回调
                        if (ContextCompat.checkSelfPermission(owner, permission)
                            == PackageManager.PERMISSION_GRANTED
                        ) {
                            function()
                        }
                    }
                permissionLauncher =
                    owner.registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                        infoDialog.dismiss()
                        if (granted) {
                            //处理业务逻辑
                            function()
                        } else {
                            if (owner.shouldShowRequestPermissionRationale(permission)) {
                                val showInfoDialog = OneBtnInfoDialogFragment().Builder().apply {
                                    content = owner.resources.getString(showRes)
                                }.create()
                                showInfoDialog.show(owner.supportFragmentManager)
                            } else {
                                val settingDialog = SettingPermissionDialog.Builder().apply {
                                    content = owner.resources.getString(setRes)
                                    launcher = setLauncher
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

    fun request(activity: FragmentActivity) {
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            infoDialog.show(activity.supportFragmentManager)
        }
        if (::permissionLauncher.isInitialized) {
            permissionLauncher.launch(permission)
        } else {
            throw NullPointerException("${this.javaClass.name}没有添加生命周期拥有者，检查lifecycle.addObserver")
        }

    }
}