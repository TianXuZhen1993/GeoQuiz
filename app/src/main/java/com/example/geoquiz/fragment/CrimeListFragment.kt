package com.example.geoquiz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geoquiz.adapter.CrimeAdapter
import com.example.geoquiz.databinding.FragmentCrimeListBinding
import com.example.geoquiz.viewmodel.CrimeListViewModel

private const val TAG = "CrimeListFragment"

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/21 20:09
 */
class CrimeListFragment : Fragment() {

    private val crimeListViewModel by viewModels<CrimeListViewModel>()
    private lateinit var binding: FragmentCrimeListBinding

    companion object {
        fun newInstance(): CrimeListFragment {
            return CrimeListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCrimeListBinding.inflate(inflater, container, false)
        binding.recyclerCrimeView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CrimeAdapter(crimeListViewModel.crimes, this@CrimeListFragment.requireActivity())
        }
        return binding.root
    }
}