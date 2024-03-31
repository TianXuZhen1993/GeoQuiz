package com.example.geoquiz.viewmodel

import android.service.autofill.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.example.geoquiz.database.Crime
import com.example.geoquiz.database.CrimeRepository
import java.util.UUID

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/21 20:06
 */
class CrimeListViewModel : ViewModel() {
    private val crimeRepository = CrimeRepository.get()

    val crimeListLiveData = crimeRepository.getCrimes()

    fun addCrime(crime: Crime){
        crimeRepository.addCrime(crime)
    }
}