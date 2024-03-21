package com.example.geoquiz.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.geoquiz.databinding.FragmentCrimeBinding
import com.example.geoquiz.entity.Crime

private const val TAG = "CrimeFragment"

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/3/21 11:37
 */
class CrimeFragment : Fragment() {
    private lateinit var binding: FragmentCrimeBinding
    private val crime = Crime()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCrimeBinding.inflate(inflater, container, false)
        binding.crimeDate.apply {
            text = crime.date.toString()
//            isEnabled = false
            setOnClickListener {
                Log.d(TAG, "onCreateView: $crime")
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
        binding.crimeTitle.addTextChangedListener {
            crime.title = it.toString()
            Log.d(TAG, "onStart: ${crime.title}")
        }
        binding.crimeSolved.setOnCheckedChangeListener { _, isChecked ->
            crime.isSolved = isChecked
        }
        Log.d(TAG, "onStart: end ${crime.toString()}")
    }
}