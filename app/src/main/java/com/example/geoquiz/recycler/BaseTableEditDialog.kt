package com.example.geoquiz.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import com.example.geoquiz.databinding.DialogBaseTableEditBinding
import com.example.library_base.dialog.base.BaseBottomDialogFragment
import com.example.library_base.utils.Logger
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/6/10 21:01
 */
class BaseTableEditDialog : BaseBottomDialogFragment() {
    private lateinit var _binding: DialogBaseTableEditBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogBaseTableEditBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        ViewCompat.setOnApplyWindowInsetsListener(_binding.clEdit) { root, windowInsets ->
//            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.ime())
//            // 此处更改的 margin，也可设置 padding，视情况而定
//            root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
//
//            }
//            Logger.onlyLog(insets.bottom.toString())
//            WindowInsetsCompat.CONSUMED
//        }
        _binding.clEdit.requestFocus()
        dialog?.window?.apply {
            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
            val insetsController = WindowCompat.getInsetsController(this, this.decorView)
            insetsController.show(WindowInsetsCompat.Type.ime())
        }
        _binding.ivClose.setOnClickListener {
            dismiss()
        }

    }

}