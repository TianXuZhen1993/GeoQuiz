package com.example.geoquiz.crime.database

import androidx.room.Entity
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
@Entity
data class Crime(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var date: Date = Date(),
    var isSolved: Boolean = false,
    var suspect: String = ""
) {
    val photoFileName
        get() = "IMG_$id.jpg"
}


