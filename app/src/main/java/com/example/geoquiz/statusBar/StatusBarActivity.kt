package com.example.geoquiz.statusBar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.geoquiz.R
import com.example.geoquiz.databinding.ActivityStatusBarBinding
import com.example.library_base.expand_fun.inflateBinding
import me.jessyan.autosize.internal.CancelAdapt


class StatusBarActivity : AppCompatActivity(), CancelAdapt {
    private val _binding: ActivityStatusBarBinding by inflateBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)
        initBar()
    }

    /**
     * 优先在这边进行处理
     */
    private fun initBar() {
        window.statusBarColor = ContextCompat.getColor(this, com.example.library_base.R.color.theme_color)
//        setFunScreen(window)
        _binding.toolBar.apply {
            setLogo(R.mipmap.ic_launcher)
            title = "状态栏设置"
        }
    }
}