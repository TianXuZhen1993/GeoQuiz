package com.example.library_base.utils

/**
 *
 * Created by XZ on 2021/8/16 09:15
 *
 * kotlin 的扩展函数
 *
 * Toast
 *
 */
fun CharSequence.toast() {
    ToastUtils.showShort(this)
}

fun Int.toast() {
    ToastUtils.showShort(this)
}
