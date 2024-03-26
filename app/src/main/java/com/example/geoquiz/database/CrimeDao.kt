package com.example.geoquiz.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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
    fun getCrime(id: UUID): LiveData<Crime?>

    @Update
    fun updateCrime(crime: Crime)

    /**
     * OnConflictStrategy:
     * NONE:无视冲突，强制插入
     * REPLACE：替换旧值，先删除后插入
     * ABORT：放弃插入，回滚事务，抛出异常
     * IGNORE : 返回错误行，忽略该行
     * @param crime
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCrime(crime: Crime)
}