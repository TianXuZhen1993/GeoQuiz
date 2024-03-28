package com.example.geoquiz.database

import android.content.Context
import android.icu.util.Calendar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.Date
import java.util.UUID
import java.util.concurrent.Executors

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/24 21:42
 */
class CrimeRepository private constructor(context: Context) {
    //升级数据库
    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {

        }
    }

    private val database: CrimeDatabase =
        Room.databaseBuilder(context.applicationContext, CrimeDatabase::class.java, DATABASE_NAME)
            .addMigrations(MIGRATION_1_2)
            .build()

    private val crimeDao = database.crimeDao()

    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()

//    fun getCrimes(): LiveData<List<Crime>> {
//        val crimes = mutableListOf<Crime>()
//        for (i in 0..10) {
//            Crime().apply {
//                title = "Title=$i"
//                isSolved = i % 2 == 0
//                crimes.add(this)
//            }
//        }
//        val crimesLiveData = MutableLiveData<List<Crime>>()
//        crimesLiveData.value = crimes
//        return crimesLiveData
//    }

    fun getCrime(id: UUID): LiveData<Crime?> = crimeDao.getCrime(id)


    private val executor = Executors.newSingleThreadExecutor()

    fun updateCrime(crime: Crime) {
        executor.execute {
            crimeDao.updateCrime(crime)
        }
    }

    fun addCrime(crime: Crime) {
        executor.execute {
            crimeDao.addCrime(crime)
        }
    }


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