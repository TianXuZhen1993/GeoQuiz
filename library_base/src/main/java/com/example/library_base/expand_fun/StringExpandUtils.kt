package com.example.library_base.expand_fun

/**
 * 超过limit限定字体，则转换成对应的符号，默认是、、、
 *
 * @param limit
 * @param tab
 * @return
 */
fun String.limitLength(limit: Int, tab: String = "..."): String {
    if (this.isEmpty()) return ""+tab
    return if (length <= limit) {
        this+tab
    } else {
        this.substring(0, limit + 1)+tab
    }
}