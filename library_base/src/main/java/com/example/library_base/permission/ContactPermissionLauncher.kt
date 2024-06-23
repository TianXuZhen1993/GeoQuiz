package com.example.library_base.permission

import android.Manifest
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/6/22 17:40
 */
class ContactPermissionLauncher(val function: () -> Unit) : DefaultLifecycleObserver {

    private lateinit var permissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        when (owner) {
            is ComponentActivity -> {
                permissionLauncher =
                    owner.registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                        if (granted) {
                            //处理业务逻辑
                            function()
                        } else {
                            if (owner.shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                                //
                            } else {
                                //TODO 去设置界面
                            }
                        }
                    }
            }

            is Fragment -> {
                permissionLauncher =
                    owner.registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                        if (granted) {
                            //处理业务逻辑
                            function()
                        } else {
                            if (owner.shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                                //
                            } else {
                                //TODO 去设置界面
                            }
                        }
                    }
            }

            else -> {
                throw NullPointerException("${this.javaClass.name}观察的生命周期不是Activity或者Fragment")
            }
        }
    }

    fun request() {
        permissionLauncher.launch(Manifest.permission.READ_CONTACTS)
    }
}