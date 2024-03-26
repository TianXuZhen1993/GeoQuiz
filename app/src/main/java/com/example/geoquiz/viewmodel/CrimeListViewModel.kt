package com.example.geoquiz.viewmodel

import androidx.lifecycle.ViewModel
import com.example.geoquiz.database.CrimeRepository

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/21 20:06
 */
class CrimeListViewModel : ViewModel() {
    private val crimeRepository = CrimeRepository.get()
    val crimeListLiveData = crimeRepository.getCrimes()
}