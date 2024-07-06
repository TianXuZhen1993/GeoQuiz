package com.example.library_base.utils

import com.example.library_base.utils.TimeUtils.getTodayZeroTime
import java.util.Calendar
import java.util.Date

/**
 * @author TXZ
 * @version 1.0
 * @date 2024/7/5 14:00
 *
 * @see getTodayZeroTime 获取当天零点时间
 */
object TimeUtils {

    /**
     * 获取当天零点时间
     * @return
     */
    fun getTodayZeroTime(range: Int = 0): Date {
        val calendar: Calendar = Calendar.getInstance()
        // 将小时、分钟、秒和毫秒都设置为0，以获取当天的零点时间
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        calendar.add(Calendar.DATE, range)
        return calendar.time
    }
}