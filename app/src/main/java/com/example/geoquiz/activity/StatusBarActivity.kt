package com.example.geoquiz.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.R
import com.example.geoquiz.databinding.ActivityStatusBarBinding
import com.example.library_base.utils.inflateBinding


class StatusBarActivity : AppCompatActivity() {
    private val _binding: ActivityStatusBarBinding by inflateBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)
        initView()
    }

    private fun initView() {
//        val decorView: View = window.decorView
//        val option = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
//        decorView.systemUiVisibility = option
//        window.statusBarColor = Color.TRANSPARENT

//        val windowInsetsController = WindowCompat.getInsetsController(window, _binding.root)
//        windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
//        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

//        WindowCompat.setDecorFitsSystemWindows(window, false)
//        window.statusBarColor = Color.TRANSPARENT

//        方法一：实现半透明效果 7.0以上的机型 ，已经被弃用
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

//        方法二：完全透明效果 ,已经被弃用
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//        window.statusBarColor = Color.TRANSPARENT

//        android 5.0~6.0版本
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        window.setStatusBarColor(Color.RED)

        //状态栏隐藏，操作屏幕，状态栏会显示出来
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        //状态栏覆盖在界面上面
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        window.statusBarColor = Color.TRANSPARENT


        //状态栏慢慢隐藏起来
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

//        //实现上面同样效果 ，代码会区分版本，进行适配
//        WindowCompat.getInsetsController(window, window.decorView)
//            .hide(WindowInsetsCompat.Type.statusBars())



        _binding.toolBar.apply {
            setLogo(R.mipmap.ic_launcher)
            title = "状态栏设置"
        }

    }
}