package com.example.library_base.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 *
 * 日期工具类
 * @author TXZ
 * @version 1.0
 * created by 2024/6/7 11:27
 */
object DateUtils {

    val defaultSimpleDateFormat: SimpleDateFormat by lazy {
        SimpleDateFormat("yyyy/MM/dd", Locale.CHINA)
    }

    val yyyy_mm_dd_SDF: SimpleDateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
    }

    val yyyymmdd_sdf: SimpleDateFormat by lazy {
        SimpleDateFormat("yyyyMMdd", Locale.CHINA)
    }


    /**
     * 判断是否是今天
     *
     * @param millis
     * @return
     */
    fun isToday(millis: Long): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val date = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
            return date.equals(LocalDate.now())
        }
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val timeInMillis0 = calendar.timeInMillis
        calendar.add(Calendar.DATE, 1)
        return millis >= timeInMillis0 && millis < calendar.timeInMillis
    }

    /**
     *
     * 获取今天星期id ，例如 [Calendar.MONDAY]
     * @return
     */
    fun getCurWeekNum(local: Locale = Locale.CHINA): Int {
        val calendar = Calendar.getInstance(local)
        return calendar.get(Calendar.DAY_OF_WEEK)
    }

    /**
     * 返回本周的星期几的日期
     *
     * @param week
     * @param firstDayOfWeek 例如：从周一开始算起，那周日是之后的第七天，如果从周日开始算起，那么周日是第一天
     * @return
     */
    fun getCurWeekDate(week: Int, firstDayOfWeek: Int = Calendar.MONDAY): Date {
        val calendar = Calendar.getInstance()
        calendar.firstDayOfWeek = firstDayOfWeek
        calendar.set(Calendar.DAY_OF_WEEK, week)
        return calendar.time
    }

    /**
     *
     *  默认是中国
     * 获取今天星期几，返回类似 周一，周五 ； 如果要长名字，请用 [Calendar.LONG]
     * @return
     */
    fun getCurWeekName(local: Locale = Locale.CHINA): String {
        val calendar = Calendar.getInstance(local)
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.CHINA) ?: ""
    }


    @JvmStatic
    fun stringFormatDate(dateString: String, sdf: SimpleDateFormat): Date? {
        return sdf.parse(dateString)
    }

    @JvmStatic
    fun dateFormatString(date: Date, sdf: SimpleDateFormat): String {
        return sdf.format(date) ?: ""
    }

    /**
     * 文字类型转换
     *
     * @param dateString
     * @param startSdf
     * @param endSdf
     * @return
     */
    fun dateStringExchange(
        dateString: String,
        startSdf: SimpleDateFormat,
        endSdf: SimpleDateFormat
    ): String {
        val date: Date = stringFormatDate(dateString, startSdf) ?: return ""
        return dateFormatString(date, endSdf)
    }

}