package com.example.geoquiz.animation

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.window.SplashScreen
import android.window.SplashScreenView
import com.example.geoquiz.databinding.ActivityAnimationBinding
import com.example.library_base.base.BaseActivity
import com.example.library_base.expand_fun.inflateBinding

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/6/11 23:01
 */
class AnimationActivity : BaseActivity() {
    private val binding: ActivityAnimationBinding by inflateBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        val animationDrawable = AnimationDrawable()
//        animationDrawable.addFrame(AppCompatResources.getDrawable(this, R.drawable.img1)!!, 50)
//        animationDrawable.addFrame(AppCompatResources.getDrawable(this,R.drawable.img2)!!,50)
    }
}