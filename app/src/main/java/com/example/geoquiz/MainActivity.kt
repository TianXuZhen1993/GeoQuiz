package com.example.geoquiz

import android.content.res.Resources.NotFoundException
import android.os.Bundle
import com.example.geoquiz.databinding.ActivityMainBinding
import com.example.library_base.base.BaseActivity
import com.example.library_base.expand_fun.inflateBinding


class MainActivity : BaseActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val binding: ActivityMainBinding by inflateBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnCrash.setOnClickListener {
            throw NotFoundException("测试crash")
        }
    }
}