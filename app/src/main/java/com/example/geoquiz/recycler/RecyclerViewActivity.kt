package com.example.geoquiz.recycler

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geoquiz.databinding.ActivityRecyclerViewBinding
import com.example.library_base.R
import com.example.library_base.base.BaseActivity
import com.example.library_base.expand_fun.inflateBinding
import com.example.library_base.expand_fun.toast

class RecyclerViewActivity : BaseActivity() {
    private val _binding: ActivityRecyclerViewBinding by inflateBinding()
    private val viewModel: BaseTableViewModel by viewModels()
    private val editDialog: BaseTableEditDialog = BaseTableEditDialog()
    private lateinit var tableBaseAdapter: TableBaseAdapter
    private var curPage: Int = 0
    private var DataLoadMode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)
        initBar()
        initView()
        initLiveData()
        _binding.layoutRefresh.autoRefresh()
    }


    private fun initBar() {
        val color = ContextCompat.getColor(this, R.color.statusBarColor)
        window.statusBarColor = color
        _binding.toolBar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initView() {
        tableBaseAdapter = TableBaseAdapter(mutableListOf())
        tableBaseAdapter.editClickListener = {
            it.customCode.toast()
            editDialog.show(supportFragmentManager, "editDialog")
        }
        _binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
            adapter = tableBaseAdapter
        }
        _binding.layoutRefresh.setOnRefreshListener {
            DataLoadMode = 0
            viewModel.getCustomTableList(0)
        }
        _binding.layoutRefresh.setOnLoadMoreListener {
            DataLoadMode = 1
            curPage++
            viewModel.getCustomTableList(curPage)
        }
    }


    private fun initLiveData() {
        viewModel.customBaseLiveData.observe(this) {
            if (DataLoadMode == 0) {
                _binding.layoutRefresh.finishRefresh()
                tableBaseAdapter.setData(it)
            } else {
                _binding.layoutRefresh.finishLoadMore()
                tableBaseAdapter.addData(it)
            }
        }
    }
}