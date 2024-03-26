package com.example.geoquiz

import android.icu.util.Calendar
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.test.platform.app.InstrumentationRegistry
import com.example.geoquiz.utils.TimeUtils
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val safeSimpleDateFormat = com.blankj.utilcode.util.TimeUtils.getSafeDateFormat("yyyy-MM-dd")
        val format = safeSimpleDateFormat.format(Calendar.getInstance())
        println(format)
    }
}
