package com.example.geoquiz.activity

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
        registerForContextMenu(binding.textView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.fragment_crime_list, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return super.onContextItemSelected(item)
    }

}