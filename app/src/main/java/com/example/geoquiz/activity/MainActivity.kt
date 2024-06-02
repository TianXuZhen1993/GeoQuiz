package com.example.geoquiz.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.example.geoquiz.R
import com.example.geoquiz.databinding.ActivityMainBinding
import com.example.geoquiz.demoNav.HomeFragment
import com.example.geoquiz.demoNav.MineFragment
import com.example.geoquiz.demoNav.WorkFragment
import com.example.library_base.base.BaseActivity
import com.example.library_base.utils.inflateBinding

class MainActivity : BaseActivity() {
    private val binding: ActivityMainBinding by inflateBinding()
    private val fragments = mutableListOf(HomeFragment(), WorkFragment(), MineFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    @SuppressLint("CommitTransaction")
    private fun initView() {
        binding.navView.itemIconTintList = null
        binding.navView.setOnItemSelectedListener {
            true
        }

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
//        binding.navView.setupWithNavController(navHostFragment.navController)
    }

    private fun showFragment(itemId: Int) {
        val fragment = when (itemId) {
            R.id.page_home -> {
                fragments[0]
            }

            R.id.page_work -> {
                fragments[1]
            }

            R.id.page_mine -> {
                fragments[2]
            }

            else -> {
                fragments[0]
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }
}