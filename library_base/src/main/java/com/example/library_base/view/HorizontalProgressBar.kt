package com.example.library_base.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.blankj.utilcode.util.SizeUtils
import com.example.library_base.R

/**
 * Created by XuZhen on 2018/11/12 11:13
 * 水平进度条
 */
class HorizontalProgressBar : View {
    //底层颜色
    private var mNormalColor = Color.parseColor("#F0F0F0")

    //完成颜色
    private var mReachColor = Color.parseColor("#4F5EC4")

    //是否开启渐变
    private var mReachColorShadowIsUsed = true

    //已完成进度条颜色（渐变-开始）
    private var mReachBarStartColor = Color.parseColor("#4857BD")

    //已完成进度条颜色（结束渐变-结束）
    private var mReachBarEndColor = Color.parseColor("#8998FD")

    //当前进度值
    private var mHorizontalProgress: Float = 0f

    //进度条最大值
    private var mHorizontalMax: Float = 100f

    //进度条高度，默认12dp
    private var mBarHeight = SizeUtils.dp2px(12f).toFloat()

    //为了看起来是圆角 ,线头是半圆，则为圆角的一半
    private var mRoundPadding = mBarHeight / 2

    //底色Paint
    private var mNormalPaint: Paint = Paint()

    //完成Paint
    private var mReachPaint: Paint = Paint()

    //横向渐变
    private lateinit var linearGradient: LinearGradient

    //是否开启动画效果
    private var isAnim = true

    //动画时长 1000 ,1s
    private var animDuration: Int = 1000

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        obtainAttributes(attrs!!)
    }


    private fun obtainAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorizontalProgressBar)
        typedArray.apply {
            mHorizontalProgress = getFloat(R.styleable.HorizontalProgressBar_HorizontalProgress, mHorizontalProgress)
            mNormalColor = getColor(R.styleable.HorizontalProgressBar_normalColor, mNormalColor)
            mReachColor = getColor(R.styleable.HorizontalProgressBar_reachColor, mReachColor)
            mReachColorShadowIsUsed =
                getBoolean(R.styleable.HorizontalProgressBar_reachShadowIsUsed, mReachColorShadowIsUsed)
            mReachBarStartColor = getColor(R.styleable.HorizontalProgressBar_reachStartColor, mReachBarStartColor)
            mReachBarEndColor = getColor(R.styleable.HorizontalProgressBar_reachEndColor, mReachBarEndColor)
            mBarHeight = getDimension(R.styleable.HorizontalProgressBar_barHeight, mBarHeight)
            mRoundPadding = mBarHeight / 2
            isAnim = getBoolean(R.styleable.HorizontalProgressBar_isAnim, true)
            animDuration = getInteger(R.styleable.HorizontalProgressBar_animDuration, animDuration)
        }
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = mBarHeight + paddingBottom + paddingTop
        setMeasuredDimension(widthSize, heightSize.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        initPaint()
        canvas.save()
        drawHorizontalProgress(canvas)
        canvas.restore()
    }

    private fun initPaint() {
        mNormalPaint.color = mNormalColor
        mNormalPaint.strokeWidth = mBarHeight
        mNormalPaint.style = Paint.Style.FILL
        mNormalPaint.isAntiAlias = true
        mNormalPaint.strokeCap = Paint.Cap.ROUND


        if (mReachColorShadowIsUsed) {
            linearGradient = LinearGradient(
                0f, 0f, width.toFloat(), 0f, mReachBarStartColor, mReachBarEndColor, Shader.TileMode.CLAMP
            )
            mReachPaint.setShader(linearGradient)
        } else {
            mReachPaint.color = mReachColor
        }
        mReachPaint.strokeWidth = mBarHeight
        mReachPaint.style = Paint.Style.FILL
        mReachPaint.isAntiAlias = true
        mReachPaint.strokeCap = Paint.Cap.ROUND


    }

    private fun drawHorizontalProgress(canvas: Canvas) {
        //移动至起始点位，x = paddingLeft  y = view的中间位置
        canvas.translate(paddingLeft.toFloat(), (height / 2).toFloat())
        canvas.drawLine(mRoundPadding, 0f, (width - paddingRight - paddingLeft - mRoundPadding), 0f, mNormalPaint)

        //当进度值在范围内，通过占比 horizontalProgress / horizontalMax  乘以总长度(width - paddingRight - paddingLeft),
        when {
            mHorizontalProgress < 0 -> {
                throw Exception("HorizontalProgressBar 值不可为负值")
            }
//            0~max
            mHorizontalProgress > 0 && mHorizontalProgress <= mHorizontalMax -> {
                canvas.drawLine(
                    mRoundPadding, 0f,
                    mHorizontalProgress / mHorizontalMax * (width - paddingRight - paddingLeft), 0f,
                    mReachPaint
                )
            }

            mHorizontalProgress >= mHorizontalMax -> {
                canvas.drawLine(0f, 0f, (width - paddingRight - paddingLeft).toFloat(), 0f, mReachPaint)
            }

        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (isAnim) startAnimDuration()
    }

    /**
     * 设置进度条值
     *
     * @param progress
     */
    fun setProgress(progress: Float) {
        mHorizontalProgress = progress
        invalidate()
    }

    fun setReachColor(reachColor: Int) {
        mReachColor = reachColor
        invalidate()
    }

    /**
     * 开始动画
     */
    fun startAnimDuration(duration: Long = 1000) {
        startAnimDurationProgress(mHorizontalProgress, duration)
    }

    private fun startAnimDurationProgress(verticalProgress: Float, duration: Long) {
        val valueAnimator = ObjectAnimator.ofFloat(0f, verticalProgress)
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.setDuration(duration)
        valueAnimator.addUpdateListener { animation ->
            val currentValue = animation.animatedValue as Float
            setProgress(currentValue)
        }
        valueAnimator.start()
    }


}
