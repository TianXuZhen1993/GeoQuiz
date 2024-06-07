package com.example.library_base.utils

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.CharacterStyle
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import java.util.regex.Pattern

/**
 *  特殊文字显示工具类
 */
object SpanUtils {

    abstract class ComClickSpan : ClickableSpan() {
        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = false
        }
    }

    /**
     * @see TextView.setSpanClick 扩展方法
     *
     * @param text 全段文字
     * @param keyWord 点击文字
     * @param colorInt 显示颜色
     * @param click 点击事件
     * @return
     */
    fun setTextViewClickSpan(text: CharSequence, keyWord: String, colorInt: Int, click: () -> Unit): SpannableString {
        val clickSpan = object : ComClickSpan() {
            override fun onClick(widget: View) {
                //去掉点击之后的背景色
                if (widget is TextView) widget.highlightColor = Color.TRANSPARENT
                click()
            }
        }
        return setClickSpan(text, keyWord, colorInt, clickSpan)
    }

    fun setClickSpan(text: CharSequence, keyWord: String, colorInt: Int, clickSpan: ClickableSpan): SpannableString {
        val spannableString = SpannableString(text)
        val pattern = Pattern.compile(keyWord)
        val matcher = pattern.matcher(spannableString)
        while (matcher.find()) {
            val foregroundColorSpan = ForegroundColorSpan(colorInt)
            val start = matcher.start()
            val end = matcher.end()
            spannableString.setSpan(clickSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(foregroundColorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return spannableString
    }


    fun setTextSize(text: String, keyWord: String, dip: Int): SpannableString {
        val sizeSpan = AbsoluteSizeSpan(dip, true)
        return matcherSearchText(text, keyWord, sizeSpan)
    }


    /**
     * 显示背景颜色
     *
     * @param text
     * @param keyWord
     * @param colorInt
     * @return
     */
    fun matcherSearchTextBGColor(text: String, keyWord: String, colorInt: Int): SpannableString {
        val foregroundSpan = BackgroundColorSpan(colorInt)
        return matcherSearchText(text, keyWord, foregroundSpan)
    }

    /**
     * 对关键词进行文字的匹配，文字显示特殊颜色
     *
     * @param text 显示文字
     * @param keyWord 关键词
     * @param colorInt 设置的颜色
     */
    fun matcherSearchTextColor(text: String, keyWord: String, colorInt: Int): SpannableString {
        val foregroundSpan = ForegroundColorSpan(colorInt)
        return matcherSearchText(text, keyWord, foregroundSpan)
    }

    /**
     *  对关键词进行文字的匹配，显示特殊样式
     *
     * @param text
     * @param keyWord
     * @return
     * @see  <a href="https://developer.android.google.cn/guide/topics/text/spans?hl=zh-cn#create-and-apply">Span具体可以查看文档</a>
     *
     */
    private fun matcherSearchText(text: String, keyWord: String, spanStyle: CharacterStyle): SpannableString {
        val span = SpannableString(text)
        val pattern = Pattern.compile(keyWord)
        val matcher = pattern.matcher(span)
        while (matcher.find()) {
            val start = matcher.start()
            val end = matcher.end()
            span.setSpan(spanStyle, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return span
    }

}