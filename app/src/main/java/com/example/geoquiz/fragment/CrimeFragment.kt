package com.example.geoquiz.fragment

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.blankj.utilcode.util.PhoneUtils
import com.example.geoquiz.R
import com.example.geoquiz.database.Crime
import com.example.geoquiz.databinding.FragmentCrimeBinding
import com.example.geoquiz.dialog.DatePickerFragment
import com.example.geoquiz.utils.IntentUtils
import com.example.geoquiz.utils.argument
import com.example.geoquiz.viewmodel.CrimeDetailViewModel
import java.util.Date
import java.util.UUID

private const val TAG = "CrimeFragment"
private const val DIALOG_DATE = "DIALOG_DATE"
private const val DATE_FORMAT = "EEE,MMM,dd"

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/3/21 11:37
 */
class CrimeFragment : Fragment() {
    private val crimeDetailViewModel by viewModels<CrimeDetailViewModel>()
    private lateinit var binding: FragmentCrimeBinding
    private var crime = Crime()

    var uuid: UUID by argument()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        crimeDetailViewModel.loadCrime(uuid)
        binding = FragmentCrimeBinding.inflate(inflater, container, false)
        binding.crimeDate.apply {
            text = crime.date.toString()
            setOnClickListener {
                DatePickerFragment().apply {
                    crimeDate = crime.date
                    show(this@CrimeFragment.parentFragmentManager, DIALOG_DATE)
                }
            }
        }
        binding.crimeTitle.addTextChangedListener {
            crime.title = it.toString()
            Log.d(TAG, "onStart: ${crime.title}")
        }
        binding.crimeSolved.setOnCheckedChangeListener { _, isChecked ->
            crime.isSolved = isChecked
        }
        binding.crimeReport.setOnClickListener {
//            Intent(Intent.ACTION_SEND).apply {
//                type = "text/plain"
//                putExtra(Intent.EXTRA_TEXT, getCrimeReport())
//                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.crime_report_subject))
//            }.also {
//                startActivity(it)
//            }

            startActivity(IntentUtils.getCallIntent("18202173767"))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeDetailViewModel.crimeLiveData.observe(viewLifecycleOwner) { crime ->
            crime?.let {
                this.crime = crime
                updateUI()
            }
        }
        parentFragmentManager.setFragmentResultListener(
            DatePickerFragment.DATE_SELECTED,
            this
        ) { _, result ->
            crime.date = result.getSerializable(DatePickerFragment.DATE_BEAN) as Date
            updateUI()
        }
    }


    override fun onStop() {
        super.onStop()
        crimeDetailViewModel.saveCrime(crime)
    }

    private fun updateUI() {
        binding.apply {
            crimeTitle.setText(crime.title)
            crimeDate.text = crime.date.toString()
            crimeSolved.isChecked = crime.isSolved
            //跳过设置动画
            crimeSolved.jumpDrawablesToCurrentState()
        }
    }

    private fun getCrimeReport(): String {
        val solvedString = if (crime.isSolved) {
            getString(R.string.crime_report_solved)
        } else {
            getString(R.string.crime_report_unsolved)
        }
        val dateString = DateFormat.format(DATE_FORMAT, crime.date).toString()
        val suspect = if (crime.suspect.isBlank()) {
            getString(R.string.crime_report_no_suspect)
        } else {
            getString(R.string.crime_report_suspect, crime.suspect)
        }
        return getString(R.string.crime_report, crime.title, dateString, solvedString, suspect)
    }
}

