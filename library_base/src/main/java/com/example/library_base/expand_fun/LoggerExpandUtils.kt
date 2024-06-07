package com.example.library_base.expand_fun

import com.example.library_base.utils.Logger


fun Any.log() {
    Logger.d(this)
}

/**
 * 快捷调试
 */
fun String.debug() {
    Logger.onlyLog(this)
}