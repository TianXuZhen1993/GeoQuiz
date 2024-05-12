package com.example.geoquiz.base

import android.app.Application
import com.example.geoquiz.BuildConfig
import com.example.geoquiz.crime.database.CrimeRepository
import com.example.geoquiz.utils.ActivityLifecycleCallBackImpl
import com.example.library_base.utils.Logger

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/24 21:50
 */
class CriminalIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        val activityLifecycleCallBackImpl = ActivityLifecycleCallBackImpl()
//        registerActivityLifecycleCallbacks(activityLifecycleCallBackImpl)
//        CrimeRepository.initialize(context = this)
        Logger.initLogUtils(BuildConfig.DEBUG)
    }
}