package com.example.geoquiz.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.library_base.base.BaseFragment
import com.example.geoquiz.databinding.FragmentHomeBinding

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/5/24 14:33
 */
class HomeFragment : BaseFragment() {
    companion object {
        private const val TAG = "HomeFragment"
    }
//    private val binding: FragmentHomeBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
}