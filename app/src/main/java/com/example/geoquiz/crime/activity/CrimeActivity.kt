package com.example.geoquiz.crime.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.databinding.FragmentCrimeBinding
import com.example.geoquiz.test.MainActivity
import com.example.library_base.expand_fun.inflateBinding
import com.example.library_base.utils.Logger

class CrimeActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "CrimeActivity"
    }

    private val binding: FragmentCrimeBinding by inflateBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.crimeReport.setOnClickListener {
            Intent(this@CrimeActivity, MainActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.d(TAG, "onDestroy: ")
    }
}