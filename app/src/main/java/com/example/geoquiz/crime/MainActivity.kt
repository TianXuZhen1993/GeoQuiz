package com.example.geoquiz.crime

import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.geoquiz.databinding.ActivityMainBinding
import com.example.geoquiz.utils.toast
import com.example.library_base.base.BaseActivity
import com.example.library_base.dialog.common.LoadingDialogFragment
import com.example.library_base.utils.inflateBinding
import com.example.library_base.utils.toast

private const val TAG = "MainActivity"


class MainActivity : BaseActivity() {
    private val binding: ActivityMainBinding by inflateBinding()

    private val activityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.also { intent ->
                }
            }
        }


    private val loadingDialog: LoadingDialogFragment by lazy {
        LoadingDialogFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }


    private fun initView() {
        binding.btnCommonDialog.setOnClickListener {
            loadingDialog.show(supportFragmentManager)
        }
    }
}