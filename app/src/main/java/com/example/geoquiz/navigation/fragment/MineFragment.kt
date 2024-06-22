package com.example.geoquiz.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.library_base.base.BaseFragment
import com.example.geoquiz.databinding.FragmentMineBinding
import com.example.library_base.utils.Logger

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/5/24 14:39
 */
class MineFragment : BaseFragment() {
    companion object {
        private const val TAG = "MineFragment"
    }

    //    private val binding: FragmentMineBinding by viewBinding()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.d(TAG, "onCreateView: ");
        val binding = FragmentMineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.d(TAG, "onDestroy: ");
    }
}