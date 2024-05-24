package com.example.geoquiz.demoNav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.geoquiz.base.BaseFragment
import com.example.geoquiz.databinding.FragmentMineBinding
import com.example.library_base.utils.viewBinding

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/5/24 14:39
 */
class MineFragment : BaseFragment() {
    private val binding: FragmentMineBinding by viewBinding()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }
}