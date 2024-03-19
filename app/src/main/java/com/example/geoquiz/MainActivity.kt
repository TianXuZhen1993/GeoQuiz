package com.example.geoquiz

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.databinding.ActivityMainBinding
import com.example.geoquiz.utils.inflateBinding
import com.example.geoquiz.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by inflateBinding()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}




