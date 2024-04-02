package com.example.geoquiz.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.geoquiz.R
import com.example.geoquiz.databinding.ActivityTwoBinding
import com.example.geoquiz.utils.inflateBinding

class TwoActivity : AppCompatActivity() {
    private val binding by inflateBinding<ActivityTwoBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.textView.setOnClickListener {
            val intent = Intent().apply {
                putExtra(MainActivity.MESSAGE, "tianxuzhen")
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}