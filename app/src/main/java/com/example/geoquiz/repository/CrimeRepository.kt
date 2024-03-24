package com.example.geoquiz.repository

import android.content.Context
import androidx.room.Room
import com.example.geoquiz.database.CrimeDatabase

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/24 21:42
 */
class CrimeRepository private constructor(context: Context) {
    companion object {
        private var INSTANCE: CrimeRepository? = null
        private const val DATABASE_NAME = "Crime_DataBase"

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE ?: throw IllegalArgumentException("CrimeRepository must be initialized")
        }
    }

    private val database: CrimeDatabase =
        Room.databaseBuilder(context.applicationContext, CrimeDatabase::class.java, DATABASE_NAME)
            .build()

    private val crimeDao = database.crimeDao()
}