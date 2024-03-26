package com.example.geoquiz.database

import androidx.room.TypeConverter
import java.util.Date
import java.util.UUID

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/24 20:54
 */
class CrimeTypeConverters {
    @TypeConverter
    fun fromData(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }
}