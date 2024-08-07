package com.example.geoquiz.navigation.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.geoquiz.R
import com.example.geoquiz.databinding.ActivityMainBinding
import com.example.geoquiz.databinding.ActivityNavBinding
import com.example.geoquiz.navigation.fragment.HomeFragment
import com.example.geoquiz.navigation.fragment.MineFragment
import com.example.geoquiz.navigation.fragment.WorkFragment
import com.example.library_base.base.BaseActivity
import com.example.library_base.utils.Logger
import com.example.library_base.expand_fun.inflateBinding

class NavActivity : BaseActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val binding: ActivityNavBinding by inflateBinding()
    private val fragments = mutableListOf(HomeFragment(), WorkFragment(), MineFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        Logger.d(TAG, "onCreate: ")
    }


    @SuppressLint("CommitTransaction")
    private fun initView() {
        binding.navView.itemIconTintList = null
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        binding.navView.setupWithNavController(navHostFragment.navController)
    }


    @SuppressLint("CommitTransaction")
    private fun initFragment() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, fragments[0])
            add(R.id.fragment_container, fragments[1])
            add(R.id.fragment_container, fragments[2])
            hide(fragments[1])
            hide(fragments[2])
        }.commit()
    }

    private var currentFragmentId = 0

    @SuppressLint("CommitTransaction")
    private fun showFragment(itemId: Int) {
        supportFragmentManager.beginTransaction().apply {
            hide(fragments[currentFragmentId])
        }.commit()
        val fragment = when (itemId) {
            R.id.page_home -> {
                currentFragmentId = 0
                fragments[0]
            }

            R.id.page_work -> {
                currentFragmentId = 1
                fragments[1]
            }

            R.id.page_mine -> {
                currentFragmentId = 2
                fragments[2]
            }

            else -> {
                currentFragmentId = 0
                fragments[0]
            }
        }
        supportFragmentManager.beginTransaction().apply {
            show(fragment)
        }.commitAllowingStateLoss()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}