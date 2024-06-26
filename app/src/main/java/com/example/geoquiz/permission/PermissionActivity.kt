package com.example.geoquiz.permission

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.geoquiz.databinding.ActivityPermissionBinding
import com.example.library_base.expand_fun.inflateBinding
import com.example.library_base.expand_fun.toast
import com.example.library_base.permission.launcher.PermissionInfoSetBackLauncher


class PermissionActivity : AppCompatActivity() {
    private val binding: ActivityPermissionBinding by inflateBinding()
    private val readPermission = PermissionInfoSetBackLauncher(Manifest.permission.READ_CONTACTS) {
        "读取联系人信息".toast()
    }.apply {
        infoRes = com.example.library_base.R.string.permission_info_call
    }

    private val writePermission =
        PermissionInfoSetBackLauncher(Manifest.permission.WRITE_CONTACTS) {
            "写入联系人信息".toast()
        }.apply {
            infoRes = com.example.library_base.R.string.permission_info_call
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(binding.root)
        lifecycle.addObserver(readPermission)
        lifecycle.addObserver(writePermission)

        binding.btnReadContacts.setOnClickListener {
            readPermission.request(this)
        }
        binding.btnWriteContacts.setOnClickListener {
            writePermission.request(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(readPermission)

    }
}


