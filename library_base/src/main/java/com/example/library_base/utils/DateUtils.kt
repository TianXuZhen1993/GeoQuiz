package com.example.library_base.utils

import java.text.SimpleDateFormat
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

    val normalSDF: SimpleDateFormat by lazy {
        SimpleDateFormat("yyyy/MM/dd", Locale.CHINA)
    }

    val yyyy_mm_dd_SDF: SimpleDateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
    }

    val yyyymmdd_sdf: SimpleDateFormat by lazy {
        SimpleDateFormat("yyyyMMdd", Locale.CHINA)
    }

    /**
     *
     * 获取今天星期id ，例如 [Calendar.MONDAY]
     * @return
     */
    fun getCurWeek(local: Locale = Locale.CHINA): Int {
        val calendar = Calendar.getInstance(local)
        return calendar.get(Calendar.DAY_OF_WEEK)
    }

    /**
     * 返回本周的星期几的日期
     *
     * @param week
     * @param firstDayOfWeek
     * @return
     */
    fun getCurWeek(week: Int, firstDayOfWeek: Int = Calendar.MONDAY): Date {
        val calendar = Calendar.getInstance()
        calendar.firstDayOfWeek = firstDayOfWeek
        calendar.set(Calendar.DAY_OF_WEEK, week)
        return calendar.time
    }

    /**
     *
     *  默认是中国
     * 获取今天星期几，返回类似 周一，周五 ； 如果要长名字，请用[Calendar.LONG]
     * @return
     */
    fun getCurWeekName(local: Locale = Locale.CHINA): String {
        val calendar = Calendar.getInstance(local)
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.CHINA) ?: ""
    }


    fun stringFormatDate(dateString: String, sdf: SimpleDateFormat): Date? {
        return sdf.parse(dateString)
    }

    fun dateFormatString(date: Date, sdf: SimpleDateFormat): String {
        return sdf.format(date) ?: ""
    }

    fun dateStringExchange(dateString: String, startSdf: SimpleDateFormat, endSdf: SimpleDateFormat): String {
        val date: Date = stringFormatDate(dateString, startSdf) ?: return ""
        return dateFormatString(date, endSdf)
    }

}