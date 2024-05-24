package com.example.geoquiz.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import com.blankj.utilcode.util.ToastUtils
import com.example.geoquiz.R
import com.example.geoquiz.databinding.ActivityMainBinding
import com.example.library_base.base.BaseActivity
import com.example.library_base.utils.inflateBinding
import com.example.library_base.utils.toast
import com.google.android.material.bottomnavigation.BottomNavigationMenuView

class MainActivity : BaseActivity() {
    private val binding: ActivityMainBinding by inflateBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    @SuppressLint("RestrictedApi")
    private fun initView() {
        binding.navView.itemIconTintList = null
        binding.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    "home".toast()

                }

                R.id.nav_work -> {
                    "work".toast()
                }

                R.id.nav_mine -> {
                    "mine".toast()
                }

                else -> {}
            }

            return@setOnItemSelectedListener true
        }
    }
}