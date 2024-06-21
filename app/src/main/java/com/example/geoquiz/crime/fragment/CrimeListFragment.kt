package com.example.geoquiz.crime.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geoquiz.R
import com.example.geoquiz.crime.adapter.CrimeAdapter
import com.example.geoquiz.crime.adapter.NewCrimeAdapter
import com.example.geoquiz.crime.database.Crime
import com.example.geoquiz.databinding.FragmentCrimeListBinding
import com.example.geoquiz.crime.viewmodel.CrimeListViewModel
import java.util.UUID

private const val TAG = "CrimeListFragment"

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/21 20:09
 */
class CrimeListFragment : Fragment(), NewCrimeAdapter.Callbacks, CrimeAdapter.Callbacks {
    private val crimeListViewModel by viewModels<CrimeListViewModel>()
    private lateinit var binding: FragmentCrimeListBinding

    private lateinit var crimeAdapter: NewCrimeAdapter

    companion object {
        fun newInstance(): CrimeListFragment {
            return CrimeListFragment()
        }
    }

    private var callbacks: Callbacks? = null

    interface Callbacks {
        fun onCrimeSelected(crimeId: UUID)
    }

    override fun onCrimeSelected(crimeId: UUID) {
        callbacks?.onCrimeSelected(crimeId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCrimeListBinding.inflate(inflater, container, false)
        binding.recyclerCrimeView.apply {
            layoutManager = LinearLayoutManager(context)
            crimeAdapter = NewCrimeAdapter()
            crimeAdapter.setCallBack(this@CrimeListFragment)
            adapter = crimeAdapter
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeListViewModel.crimeListLiveData.observe(viewLifecycleOwner) { crimes ->
            crimeAdapter.submitList(crimes)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        callbacks = null
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.fragment_crime_list, menu)
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_crime -> {
                val crime = Crime()
                crimeListViewModel.addCrime(crime)
                callbacks?.onCrimeSelected(crime.id)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}