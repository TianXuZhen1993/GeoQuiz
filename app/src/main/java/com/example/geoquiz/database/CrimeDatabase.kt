package com.example.geoquiz.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/24 20:25
 */
@Database(entities = [Crime::class], version = 2, exportSchema = false)
@TypeConverters(CrimeTypeConverters::class)
abstract class CrimeDatabase : RoomDatabase() {
    abstract fun crimeDao(): CrimeDao
}

/*
exportSchema 禁止导出数据库
 */