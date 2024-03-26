package com.tian.utilsproject.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast

object ToastUtils {
    fun showCenterShort(context: Context, text: CharSequence) {
        val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }

    fun showShort(context: Context, text: CharSequence) {
        val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        toast.show()
    }
}