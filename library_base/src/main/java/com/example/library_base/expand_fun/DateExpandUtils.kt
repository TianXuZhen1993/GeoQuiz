package com.example.library_base.expand_fun

import com.example.library_base.utils.DateUtils
import java.text.SimpleDateFormat
import java.util.Date

fun Date.format(sdf: SimpleDateFormat = DateUtils.defaultSimpleDateFormat): String {
    return sdf.format(this)
}