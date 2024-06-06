package com.example.geoquiz.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import com.example.geoquiz.R
import com.example.geoquiz.databinding.ActivityStatusBarBinding
import com.example.geoquiz.utils.AppBarUtils
import com.example.library_base.utils.Logger
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
        _binding.toolBar.apply {
            setLogo(R.mipmap.ic_launcher)
            title = "状态栏设置"
        }
    }

    //参考资料
    //https://juejin.cn/post/7038422081528135687
    //https://juejin.cn/post/7376851823640870921
    /**
     * @param toolBar
     */
    fun setFullScreen(toolBar: View) {
        //全面屏
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        //要求Android11 以上
//        toolBar.setOnApplyWindowInsetsListener { v, insets ->
//            insets.systemWindowInsetTop
//            insets
//        }
        ViewCompat.setOnApplyWindowInsetsListener(toolBar) { view, windowInserts ->
            val insets = windowInserts.getInsets(WindowInsetsCompat.Type.statusBars())
            view.updateLayoutParams<MarginLayoutParams> {
                topMargin = insets.top
                marginStart = insets.left
                marginEnd = insets.right
                bottomMargin = insets.bottom
            }
            WindowInsetsCompat.CONSUMED
        }

    }
}