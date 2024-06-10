package com.example.library_base.utilsTest

import com.example.library_base.utils.DateUtils
import org.junit.Assert
import org.junit.Test
import java.util.Calendar

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/6/7 23:26
 */
class DateUtilsTest {
    @Test
    fun isToday() {
        Assert.assertTrue(DateUtils.isToday(Calendar.getInstance().timeInMillis))
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, 1)
        println(DateUtils.dateFormatString(calendar.time, DateUtils.defaultSimpleDateFormat))
        Assert.assertFalse(DateUtils.isToday(calendar.timeInMillis))
        calendar.add(Calendar.DATE, -2)
        println(DateUtils.dateFormatString(calendar.time, DateUtils.defaultSimpleDateFormat))
        Assert.assertFalse(DateUtils.isToday(calendar.timeInMillis))
    }

    @Test
    fun getLocalDate() {

    }
}