package com.example.geoquiz.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import com.blankj.utilcode.util.ToastUtils

object ToastUtils {
    fun showCenterShort(context: Context, text: CharSequence) {
        val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    fun showShort(context: Context, text: CharSequence) {
        val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        toast.show()
    }
}

fun String.toast() {
    ToastUtils.showShort(this)
}