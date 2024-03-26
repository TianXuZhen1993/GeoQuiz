package com.example.geoquiz.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.geoquiz.databinding.FragmentCrimeBinding
import com.example.geoquiz.database.Crime
import com.example.geoquiz.utils.argument
import com.example.geoquiz.viewmodel.CrimeListViewModel
import java.util.UUID

private const val TAG = "CrimeFragment"

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/3/21 11:37
 */
class CrimeFragment : Fragment() {
    private val crimeListViewModel by viewModels<CrimeListViewModel>()
    private lateinit var binding: FragmentCrimeBinding
    private val crime = Crime()

    var uuid: UUID by argument()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCrimeBinding.inflate(inflater, container, false)
        binding.crimeDate.apply {
            text = crime.date.toString()
            isEnabled = false
        }
        binding.crimeTitle.addTextChangedListener {
            crime.title = it.toString()
            Log.d(TAG, "onStart: ${crime.title}")
        }
        binding.crimeSolved.setOnCheckedChangeListener { _, isChecked ->
            crime.isSolved = isChecked
        }
        return binding.root
    }
}