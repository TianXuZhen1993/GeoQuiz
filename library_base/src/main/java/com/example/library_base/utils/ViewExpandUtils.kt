package com.example.library_base.utils

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText


/**
 *
 * EditText 显示密码、不显示密码
 * @param isShow
 */
fun EditText.passwordIsShow(isShow: Boolean) {
    transformationMethod = if (isShow) HideReturnsTransformationMethod.getInstance()
    else PasswordTransformationMethod.getInstance()
}
