package com.example.geoquiz.crime

import android.graphics.Color
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.geoquiz.databinding.ActivityMainBinding
import com.example.library_base.base.BaseActivity
import com.example.library_base.dialog.common.LoadingDialogFragment
import com.example.library_base.utils.SpanUtils
import com.example.library_base.utils.inflateBinding
import com.example.library_base.utils.setSpanClick
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
            "点击成功".toast()
        }
//        binding.tv.setSpanClick("天下无贼好人", "贼好", Color.BLUE) {
//            "点击成功".toast()
//        }
        binding.tv.text = SpanUtils.setTextSize("天下无贼", "贼",22)
    }
}