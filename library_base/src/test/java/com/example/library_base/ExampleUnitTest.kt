package com.example.library_base

import com.example.library_base.utils.DateUtils
import org.junit.Assert.*
import org.junit.Test
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val nowTime = System.currentTimeMillis()
        val nowDate = Date(nowTime)
        val formatWeek = SimpleDateFormat("EEEE", Locale.CHINA)
        val nowWeek = formatWeek.format(nowDate)
        println(nowWeek)
    }
}