package com.tian.utilsproject.utils

import android.text.SpannableString
import android.text.Spanned
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import java.util.regex.Pattern

object SpanUtils {


    /**
     * TODO 对关键词进行文字的匹配，显示特殊颜色
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
     * TODO 对关键词进行文字的匹配，显示特殊样式
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