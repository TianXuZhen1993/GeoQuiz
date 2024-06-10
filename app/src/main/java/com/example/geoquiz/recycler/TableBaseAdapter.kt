package com.example.geoquiz.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geoquiz.databinding.ItemTableBaseBinding

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/6/10 1:26
 */
class TableBaseAdapter(private val beans: MutableList<CustomBaseTableBean>) :
    RecyclerView.Adapter<TableBaseAdapter.TableBaseViewHolder>() {

    override fun getItemCount(): Int = beans.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableBaseViewHolder {
        val binding =
            ItemTableBaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TableBaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TableBaseViewHolder, position: Int) {
        holder.setData(beans[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<CustomBaseTableBean>) {
        beans.clear()
        lastData.clear()
        beans.addAll(data)
        notifyDataSetChanged()
    }

    private var lastData = mutableListOf<CustomBaseTableBean>()
    fun addData(data: MutableList<CustomBaseTableBean>) {
        lastData = beans
        beans.addAll(data)
        notifyItemRangeInserted(lastData.size, beans.size)
    }

    lateinit var editClickListener: (CustomBaseTableBean) -> Unit

    inner class TableBaseViewHolder(val binding: ItemTableBaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnEdit.setOnClickListener {
                val customBaseTableBean = beans[adapterPosition]
                if (::editClickListener.isInitialized) editClickListener(customBaseTableBean)
            }
        }

        fun setData(bean: CustomBaseTableBean) {
            binding.tvTitle.text = bean.customName
            binding.tvCustomCode.text = bean.customCode
            binding.tvCustomNum.text = bean.number
        }
    }


}