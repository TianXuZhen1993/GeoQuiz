package com.example.geoquiz.demoNav

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.geoquiz.base.BaseFragment
import com.example.geoquiz.databinding.FragmentCrimeBinding
import com.example.library_base.utils.Logger
import com.example.library_base.utils.logD

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
        val binding = FragmentCrimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.d(TAG, "onCreate")
    }


    override fun onResume() {
        super.onResume()
        Logger.d(TAG, "onResume: ");
    }

}