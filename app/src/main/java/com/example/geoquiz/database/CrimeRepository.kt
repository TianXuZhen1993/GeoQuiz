package com.example.geoquiz.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.UUID

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/24 21:42
 */
class CrimeRepository private constructor(context: Context) {
    private val migration_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {

        }
    }

    private val database: CrimeDatabase =
        Room.databaseBuilder(context.applicationContext, CrimeDatabase::class.java, DATABASE_NAME)
            .addMigrations(migration_1_2)
            .build()

    private val crimeDao = database.crimeDao()

    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()

    fun getCrime(id: UUID): LiveData<Crime?> = crimeDao.getCrime(id)

    companion object {
        private var INSTANCE: CrimeRepository? = null
        private const val DATABASE_NAME = "crime-database"

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE ?: throw IllegalArgumentException("CrimeRepository must be initialized")
        }
    }

}