package com.example.geoquiz.entity

import androidx.annotation.StringRes

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/3/15 14:16
 */
data class Question(@StringRes val textResId: Int, val answer: Boolean)

