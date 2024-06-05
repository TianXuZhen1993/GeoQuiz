package com.example.geoquiz.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import com.example.geoquiz.R
import com.example.geoquiz.databinding.ActivityStatusBarBinding
import com.example.geoquiz.utils.AppBarUtils
import com.example.library_base.utils.inflateBinding
import me.jessyan.autosize.internal.CancelAdapt


class StatusBarActivity : AppCompatActivity(), CancelAdapt {
    private val _binding: ActivityStatusBarBinding by inflateBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)
        initView()
    }

    private fun initView() {
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

        //状态栏慢慢隐藏起来
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

//        //实现上面同样效果 ，代码会区分版本，进行适配
//        WindowCompat.getInsetsController(window, window.decorView).apply {
//            hide(WindowInsetsCompat.Type.statusBars())
//        }

//        window.statusBarColor = Color.TRANSPARENT

//        window.decorView.setOnApplyWindowInsetsListener { _, insets ->
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                insets.getInsets(WindowInsetsCompat.Type.statusBars()).top.logD()
//                insets.getInsets(WindowInsetsCompat.Type.statusBars()).bottom.logD()
//            } else {
//                insets.systemWindowInsetTop.logD()
//            }
//            return@setOnApplyWindowInsetsListener insets
//        }

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT


        _binding.toolBar.apply {
            setLogo(R.mipmap.ic_launcher)
            title = "状态栏设置"
        }
    }
}