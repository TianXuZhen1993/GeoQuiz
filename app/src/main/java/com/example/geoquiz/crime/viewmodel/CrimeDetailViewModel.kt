package com.example.geoquiz.crime.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.geoquiz.crime.database.Crime
import com.example.geoquiz.crime.database.CrimeRepository
import java.io.File
import java.util.UUID

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/3/26 10:56
 */
class CrimeDetailViewModel : ViewModel() {
    private val crimeRepository = CrimeRepository.get()

    private val crimeIdLiveData = MutableLiveData<UUID>()

    var crimeLiveData: LiveData<Crime?> = crimeIdLiveData.switchMap { crimeId ->
        crimeRepository.getCrime(crimeId)
    }


    fun loadCrime(crimeId: UUID) {
        crimeIdLiveData.value = crimeId
    }

    fun saveCrime(crime: Crime) {
        crimeRepository.updateCrime(crime)
    }

    fun getPhotoFile(crime: Crime): File = crimeRepository.getPhotoFile(crime)
}