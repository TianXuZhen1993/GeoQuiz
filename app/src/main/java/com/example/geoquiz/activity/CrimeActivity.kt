package com.example.geoquiz.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.databinding.FragmentCrimeBinding
import com.example.library_base.utils.inflateBinding

class CrimeActivity : AppCompatActivity() {
    private val _binding: FragmentCrimeBinding by inflateBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)
    }
}