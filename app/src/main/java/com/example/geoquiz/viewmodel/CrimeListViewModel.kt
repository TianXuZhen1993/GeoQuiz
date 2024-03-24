package com.example.geoquiz.viewmodel

import androidx.lifecycle.ViewModel
import com.example.geoquiz.entity.Crime

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/21 20:06
 */
class CrimeListViewModel : ViewModel() {
    val crimes = mutableListOf<Crime>()

    init {
        for (i in 0 until 20) {
            val crime = Crime()
            crime.title = "Crime $i"
            crime.isSolved = i % 2 == 0
            crimes += crime
        }
    }
}