package com.example.library_base.utils

import android.os.Build
import com.example.library_base.utils.DateUtils.isToday
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
 *
 * @see isToday 判断一个时间戳是否是今天
 */
object DateUtils {

    val defaultFormat: SimpleDateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA)
    }

    /**
     * 判断是否是今天
     *
     * @param millis
     * @return
     */
    fun isToday(timestamp: Long): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 使用 java.time 包 (API 26 及以上)
            val date = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate()
            val today = LocalDate.now()
            date.isEqual(today)
        } else {
            // 使用 Calendar 类 (API 26 以下)
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timestamp
            val today = Calendar.getInstance()
            calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                    calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)
        }
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