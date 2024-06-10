package com.example.geoquiz.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import com.example.geoquiz.databinding.DialogBaseTableEditBinding
import com.example.library_base.dialog.base.BaseBottomDialogFragment
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
        dialog?.window?.decorView?.setPadding(0, 0, 0, 0)
        ViewCompat.setOnApplyWindowInsetsListener(_binding.root) { root, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.ime())
            // 此处更改的 margin，也可设置 padding，视情况而定
            root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = 0
                leftMargin = 0
                bottomMargin = insets.bottom
                rightMargin = 0
            }
            WindowInsetsCompat.CONSUMED
        }
        _binding.ivClose.setOnClickListener {
            dismiss()
        }
    }
}