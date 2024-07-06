package com.example.geoquiz.permission

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.geoquiz.databinding.ActivityPermissionBinding
import com.example.library_base.expand_fun.inflateBinding
import com.example.library_base.utils.AppUtils


class PermissionActivity : AppCompatActivity() {
    private val binding: ActivityPermissionBinding by inflateBinding()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(binding.root)


        binding.btnReadContacts.setOnClickListener {
            AppUtils.getAppActivity()
        }
        binding.btnWriteContacts.setOnClickListener {

        }
    }
}


