package com.example.library_base.utils

import android.content.Intent

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/5/31 13:47
 */

/**
 * 设置最初的界面
 *
 */
fun Intent.addHomeActivityFlag() {
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
}

/**
 * 如果栈中存在，则关闭上面界面，以及重建界面
 * 如果不存在，则新建界面
 *
 */
fun Intent.clearTopAndReCreate() {
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
}
