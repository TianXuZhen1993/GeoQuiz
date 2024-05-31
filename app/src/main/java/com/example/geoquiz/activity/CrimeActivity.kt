package com.example.geoquiz.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.databinding.FragmentCrimeBinding
import com.example.library_base.utils.Logger
import com.example.library_base.utils.inflateBinding

class CrimeActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "CrimeActivity"
    }

    private val binding: FragmentCrimeBinding by inflateBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Logger.d(TAG, "onCreate: ")
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