package com.example.geoquiz.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.geoquiz.databinding.ListItemCrimeBinding
import com.example.geoquiz.entity.Crime


/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/21 23:52
 */
class CrimeAdapter(var crimes: MutableList<Crime>, val context: Context) :
    RecyclerView.Adapter<CrimeAdapter.CrimeViewHolder>() {

    inner class CrimeViewHolder(private val binding: ListItemCrimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var crime: Crime

        init {
            binding.root.setOnClickListener {
                Toast.makeText(context, "点击了 ${crime.title}", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        fun bind(crime: Crime) {
            this.crime = crime
            binding.crimeTitle.text = crime.title
            binding.crimeDate.text = crime.date.toString()
            binding.imgCrimeSolved.visibility = if (crime.isSolved) View.VISIBLE else View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeViewHolder {
        val crimeBinding =
            ListItemCrimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CrimeViewHolder(crimeBinding)
    }

    override fun getItemCount(): Int = crimes.size

    override fun onBindViewHolder(holder: CrimeViewHolder, position: Int) {
        holder.bind(crimes[position])
    }
}