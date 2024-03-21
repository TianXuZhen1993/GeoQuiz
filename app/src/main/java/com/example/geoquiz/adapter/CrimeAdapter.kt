package com.example.geoquiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geoquiz.databinding.ListItemCrimeBinding
import com.example.geoquiz.entity.Crime

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/21 23:52
 */
class CrimeAdapter(var crimes: MutableList<Crime>) :
    RecyclerView.Adapter<CrimeAdapter.CrimeViewHolder>() {

    inner class CrimeViewHolder(private val binding: ListItemCrimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(crime: Crime) {
            binding.crimeTitle.text = crime.title
            binding.crimeDate.text = crime.date.toString()
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