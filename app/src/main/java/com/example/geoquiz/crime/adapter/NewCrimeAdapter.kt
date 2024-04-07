package com.example.geoquiz.crime.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.geoquiz.crime.database.Crime
import com.example.geoquiz.databinding.ListItemCrimeBinding
import java.util.UUID


/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/21 23:52
 */
class NewCrimeAdapter : ListAdapter<Crime, NewCrimeAdapter.NewCrimeViewHolder>(CrimeDiffCallBack) {

    interface Callbacks {
        fun onCrimeSelected(crimeId: UUID)
    }

    private var callbacks: Callbacks? = null

    fun setCallBack(callbacks: Callbacks) {
        this.callbacks = callbacks
    }

    object CrimeDiffCallBack : DiffUtil.ItemCallback<Crime>() {
        override fun areItemsTheSame(oldItem: Crime, newItem: Crime): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Crime, newItem: Crime): Boolean {
            return oldItem == newItem
        }
    }

    inner class NewCrimeViewHolder(private val binding: ListItemCrimeBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewCrimeViewHolder {
        val crimeBinding = ListItemCrimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewCrimeViewHolder(crimeBinding)
    }

    override fun onBindViewHolder(holder: NewCrimeViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}