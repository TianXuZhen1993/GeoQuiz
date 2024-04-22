package com.example.geoquiz.utils

/**
 * 对中文文字进行字数限制，英文数字暂不支持
 *
 * @param limit 限制的字数
 * @param tab 末尾以tab样式显示
 * @return 返回限制后的字符
 */
fun String.limitLength(limit: Int, tab: String = "..."): String {
    if (this.isEmpty()) return ""+tab
    return if (length <= limit) {
        this+tab
    } else {
        this.substring(0, limit + 1)+tab
    }
}

