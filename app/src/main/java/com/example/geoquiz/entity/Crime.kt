package com.example.geoquiz.entity

import java.util.Date
import java.util.UUID


/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/3/21 11:29
 */
data class Crime(
    val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var date: Date = Date(),
    var isSolved: Boolean = false
)
