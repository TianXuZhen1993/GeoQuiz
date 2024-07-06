package com.example.library_base.utilsTest

import com.example.library_base.utils.TimeUtils
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/7/6 0:13
 */
class TimeUtilsTest {

    @Test
    fun getZeroTime() {
        val simpleFormat = SimpleDateFormat("yyyyMMdd")
        val date = TimeUtils.getTodayZeroTime()
        assertEquals(
            "当range是默认值的时候，显示的是当天",
            simpleFormat.format(date), simpleFormat.format(Date())
        )
        val lastDay = TimeUtils.getTodayZeroTime(-1)
        assertEquals(
            "当range是-1的时候，显示的是昨天",
            simpleFormat.format(lastDay), "20240705"
        )
        val nextDay = TimeUtils.getTodayZeroTime(1)
        assertEquals(
            "当range是1的时候，显示的是昨天",
            simpleFormat.format(nextDay), "20240707"
        )
    }
}