package com.example.geoquiz.crime.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.geoquiz.utils.argument
import java.util.Date



/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/27 23:08
 */
class DatePickerFragment : DialogFragment() {

    var crimeDate: Date by argument(Date())

    companion object{
         const val DATE_SELECTED = "DATE_SELECTED"
         const val DATE_BEAN = "DATE_BEAN"

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val onDateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            parentFragmentManager.setFragmentResult(
                DATE_SELECTED, Bundle().apply { putSerializable(DATE_BEAN, calendar.time)
                }
            )
        }


        val calendar = Calendar.getInstance()
        calendar.time = crimeDate
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireContext(), onDateSetListener, year, month, day)
    }
}