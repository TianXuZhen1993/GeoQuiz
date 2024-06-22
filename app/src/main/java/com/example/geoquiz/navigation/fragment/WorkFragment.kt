package com.example.geoquiz.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.library_base.base.BaseFragment
import com.example.geoquiz.databinding.FragmentCrimeBinding
import com.example.library_base.utils.Logger

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/5/24 14:39
 */
class WorkFragment : BaseFragment() {
//    private val binding: FragmentCrimeBinding by viewBinding()

    companion object {
        private const val TAG = "WorkFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.d(TAG, "onCreateView: ");
        val binding = FragmentCrimeBinding.inflate(inflater, container, false)
//        resources.getString(R.string.)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.d(TAG, "onDestroy: ");
    }

}