package com.example.geoquiz.base

import android.app.Application
import com.example.geoquiz.database.CrimeRepository

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/24 21:50
 */
class CriminalIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(context = this)
    }
}