package com.example.library_base.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Shader
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.library_base.R


/**
 *
 * 流光字体
 * @author TXZ
 * @version 1.0
 * created by 2024/5/23 9:32
 */
class ShineTextView : AppCompatTextView {
    //是否开启流光，默认开启
    private var isShine = true

    //默认是流光效果  0 流光  1 注入效果
    private var shineType = 0
    //流光效果下字体流动次数
    private var shineCount: Int = 3
    //注入效果 开始，中间，结束
    private var startColor: Int = Color.BLACK
    private var shineColor: Int = Color.WHITE
    private var endColor: Int = Color.BLACK
    //一次动效时长
    private var shineDuration: Int = 2000

    private var _count: Int = 0 //自行运行动画次数

    private lateinit var mLinearGradient: LinearGradient
    private var mGradientMatrix: Matrix = Matrix()
    private var mViewWidth = 0
    private var mTranslate = 0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        obtainAttributes(attrs!!)
    }


    private fun obtainAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShineTextView)
        typedArray.apply {
            isShine = typedArray.getBoolean(R.styleable.ShineTextView_isShine, isShine)
            shineType = typedArray.getInt(R.styleable.ShineTextView_shineType, shineType)
            startColor = typedArray.getColor(R.styleable.ShineTextView_startColor, startColor)
            shineColor = typedArray.getColor(R.styleable.ShineTextView_midColor, shineColor)
            endColor = typedArray.getColor(R.styleable.ShineTextView_endColor, endColor)
            shineCount = typedArray.getInt(R.styleable.ShineTextView_shineCount, shineCount)
            shineDuration =
                typedArray.getInt(R.styleable.ShineTextView_shineDuration, shineDuration)
        }
        typedArray.recycle()
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (isShine) {
            mViewWidth = measuredWidth
            when (shineType) {
                0 -> {
                    //流光效果
                    mLinearGradient = LinearGradient(
                        0f,
                        0f,
                        (mViewWidth / 8).toFloat(),
                        0f,
                        intArrayOf(currentTextColor, shineColor, currentTextColor),
                        null,
                        Shader.TileMode.CLAMP
                    )
                }

                1 -> {
                    mLinearGradient = LinearGradient(
                        0f,
                        0f,
                        (mViewWidth / 8).toFloat(),
                        0f,
                        intArrayOf(endColor, shineColor, startColor),
                        null,
                        Shader.TileMode.CLAMP
                    )
                }
            }
            paint.shader = mLinearGradient
        }
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!isShine) return
        mTranslate += mViewWidth / (shineDuration / 50)
        if (mTranslate > 1.2 * mViewWidth) {
            mTranslate = -mViewWidth / 5
            _count++
        }
        mGradientMatrix.setTranslate(mTranslate.toFloat(), 0f)
        mLinearGradient.setLocalMatrix(mGradientMatrix)
        when {
            shineType == 0 && _count < shineCount -> postInvalidateDelayed(50)
            shineType == 1 && _count < 1 -> postInvalidateDelayed(50)
            else -> {
                mGradientMatrix.setTranslate((1.2 * mViewWidth).toFloat(), 0f)
                mLinearGradient.setLocalMatrix(mGradientMatrix)
            }
        }
    }

}