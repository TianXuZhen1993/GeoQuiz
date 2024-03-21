package com.example.geoquiz.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.geoquiz.R
import com.example.geoquiz.databinding.ActivityMainBinding
import com.example.geoquiz.fragment.CrimeFragment
import com.example.geoquiz.fragment.CrimeListFragment
import com.example.geoquiz.utils.inflateBinding
import com.example.geoquiz.viewmodel.MainViewModel

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by inflateBinding()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            val fragment = CrimeListFragment.newInstance()
            supportFragmentManager.commit {
                add(R.id.fragment_container, fragment)
            }
        }
    }
}