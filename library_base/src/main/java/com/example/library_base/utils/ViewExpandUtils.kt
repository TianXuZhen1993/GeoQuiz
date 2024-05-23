package com.example.library_base.utils

import android.text.method.HideReturnsTransformationMethod
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.TextView


/**
 *
 * EditText 显示密码、不显示密码
 * @param isShow
 */
fun EditText.passwordIsShow(isShow: Boolean) {
    transformationMethod = if (isShow) HideReturnsTransformationMethod.getInstance()
    else PasswordTransformationMethod.getInstance()
}

/**
 * TextView 文字可点击事件
 *
 * @param text
 * @param keyWord
 * @param colorInt
 * @param click
 */
fun TextView.setSpanClick(text: CharSequence, keyWord: String, colorInt: Int, click: () -> Unit) {
    movementMethod = LinkMovementMethod.getInstance()
    this.text = SpanUtils.setTextViewClickSpan(text, keyWord, colorInt, click)
}
