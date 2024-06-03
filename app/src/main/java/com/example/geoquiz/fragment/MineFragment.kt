package com.example.geoquiz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.geoquiz.base.BaseFragment
import com.example.geoquiz.databinding.FragmentMineBinding
import com.example.library_base.utils.AppUtils
import com.example.library_base.utils.Logger
import com.example.library_base.utils.logD
import com.example.library_base.utils.toast

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
        binding.btLogin.setOnClickListener {
            AppUtils.isAppInstalled("com.chailease.zlsales").toString().toast()
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.d(TAG, "onDestroy: ");
    }
}