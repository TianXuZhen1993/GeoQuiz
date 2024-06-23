package com.example.geoquiz.permission

import android.Manifest
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.geoquiz.R
import com.example.geoquiz.databinding.ActivityPermissionBinding
import com.example.library_base.expand_fun.inflateBinding
import com.example.library_base.expand_fun.setFunScreen
import com.example.library_base.expand_fun.toast
import com.example.library_base.permission.PermissionInfoDialog
import com.example.library_base.permission.PermissionInfoLauncher
import com.example.library_base.permission.PermissionLauncher

class PermissionActivity : AppCompatActivity() {
    private val binding: ActivityPermissionBinding by inflateBinding()
    private val callPermission = PermissionInfoLauncher(Manifest.permission.CALL_PHONE) {
        callNum()
    }

    private fun callNum() {
        "拨打电话".toast()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        lifecycle.addObserver(callPermission)
        val permissionInfoDialog = PermissionInfoDialog()
        binding.btnCall.setOnClickListener {
            callPermission.request()
//            permissionInfoDialog.show(supportFragmentManager)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(callPermission)
    }

}