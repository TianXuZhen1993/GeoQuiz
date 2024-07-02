package com.example.library_base.permission

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.library_base.R
import com.example.library_base.dialog.common.OneBtnInfoDialogFragment
import com.example.library_base.expand_fun.locationServiceIsOpen
import com.example.library_base.permission.dialog.PermissionInfoDialog
import com.example.library_base.permission.dialog.SettingPermissionDialog
import com.example.library_base.utils.CoreUtils

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * @date 2024/7/2 10:29
 */
class LocationPermissionLauncher(private val location: () -> Unit) : DefaultLifecycleObserver {

    private val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)

    @StringRes
    var infoRes: Int = R.string.location_service_refuse

    @StringRes
    var showRes: Int = R.string.permission_refuse_location

    @StringRes
    var setRes: Int = R.string.permission_set_location

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    private lateinit var setLauncher: ActivityResultLauncher<Intent>

    private lateinit var locationServiceLauncher: ActivityResultLauncher<Intent>

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
                            startLocation(owner)
                        } else {
                            //权限没有同意，进入设置界面
                            val settingDialog = SettingPermissionDialog.Builder().apply {
                                content = owner.resources.getString(setRes)
                                launcher = setLauncher
                            }.create()
                            settingDialog.show(owner.supportFragmentManager)
                        }
                    }
//                权限设置
                setLauncher = owner.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    //设置界面返回，重新检核权限
                    var isGranted = true
                    permissions.forEach { permission ->
                        isGranted = isGranted and (ContextCompat.checkSelfPermission(
                            owner, permission
                        ) == PackageManager.PERMISSION_GRANTED)
                    }
                    //设置界面回调
                    if (isGranted) {
                        startLocation(owner)
                    } else {
                        //没有开启权限
                        OneBtnInfoDialogFragment().Builder().apply {
                            content = owner.resources.getString(showRes)
                        }.create().show(owner.supportFragmentManager)
                    }
                }
                //定位服务没有开启
                locationServiceLauncher =
                    owner.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                        if (owner.locationServiceIsOpen()) {
                            //开启定位
                            location()
                        } else {
                            val refuseDialog = OneBtnInfoDialogFragment().Builder().apply {
                                content = owner.resources.getString(R.string.location_service_refuse)
                            }.create()
                            refuseDialog.show(owner.supportFragmentManager)
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
        setLauncher.unregister()
        locationServiceLauncher.unregister()
    }

    fun request(activity: FragmentActivity) {
        var isGranted = true
        permissions.forEach {
            isGranted = isGranted and (ContextCompat.checkSelfPermission(
                activity, it
            ) == PackageManager.PERMISSION_GRANTED)
        }
        //先判断权限，是否开启
        if (isGranted) {
            //权限开启，先开启定位服务
            startLocation(activity)
            return
        }
        //权限没有开启，显示INFO信息
        infoDialog.show(activity.supportFragmentManager)
        if (::permissionLauncher.isInitialized) {
            permissionLauncher.launch(permissions)
        } else {
            throw NullPointerException("${this.javaClass.name}没有添加生命周期拥有者，检查lifecycle.addObserver")
        }
    }

    /**
     * 对定位功能进行包装，Location 之前判断 定位服务是否开启，没有开启，怎线开启定位
     * @param activity
     */
    private fun startLocation(activity: FragmentActivity) {
        if (activity.locationServiceIsOpen()) {
            location()
        } else {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            locationServiceLauncher.launch(intent)
        }
    }
}