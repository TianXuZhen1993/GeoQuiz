package com.example.geoquiz.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID


/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/3/21 11:29
 */
@Entity(indices = [Index("id", "title"), Index("date")])
data class Crime(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var date: Date = Date(),
    var isSolved: Boolean = false
)
