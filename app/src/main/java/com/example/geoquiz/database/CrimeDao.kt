package com.example.geoquiz.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import java.util.UUID

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/24 21:24
 */
@Dao
interface CrimeDao {
    @Query("SELECT * From crime")
    fun getCrimes(): LiveData<List<Crime>>

    @Query("SELECT * From crime where id=(:id)")
    fun getCrime(id: UUID):LiveData<Crime>?
}