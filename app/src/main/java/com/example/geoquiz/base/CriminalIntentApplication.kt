package com.example.geoquiz.base

import android.app.Application
import com.example.geoquiz.crime.database.CrimeRepository
import com.example.geoquiz.utils.ActivityLifecycleCallBackImpl

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/24 21:50
 */
class CriminalIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val activityLifecycleCallBackImpl = ActivityLifecycleCallBackImpl()
        registerActivityLifecycleCallbacks(activityLifecycleCallBackImpl)
        CrimeRepository.initialize(context = this)
    }
}