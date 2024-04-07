package com.example.geoquiz.crime.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geoquiz.databinding.ListItemCrimeBinding
import com.example.geoquiz.crime.database.Crime
import java.util.UUID


/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/21 23:52
 */
class CrimeAdapter(private var crimes: List<Crime>) :
    RecyclerView.Adapter<CrimeAdapter.CrimeViewHolder>() {

    interface Callbacks {
        fun onCrimeSelected(crimeId: UUID)
    }

    private var callbacks: Callbacks? = null

    fun setCallBack(callbacks: Callbacks) {
        this.callbacks = callbacks
    }

    inner class CrimeViewHolder(private val binding: ListItemCrimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var crime: Crime

        init {
            binding.root.setOnClickListener {
                callbacks?.onCrimeSelected(crime.id)
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

    fun setData(crimes: List<Crime>) {
        this.crimes = crimes
        notifyDataSetChanged()
    }
}