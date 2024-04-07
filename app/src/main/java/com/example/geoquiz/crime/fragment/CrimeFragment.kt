package com.example.geoquiz.crime.fragment

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.geoquiz.R
import com.example.geoquiz.crime.database.Crime
import com.example.geoquiz.crime.dialog.DatePickerFragment
import com.example.geoquiz.crime.viewmodel.CrimeDetailViewModel
import com.example.geoquiz.databinding.FragmentCrimeBinding
import com.example.geoquiz.utils.ScreenUtils
import com.example.geoquiz.utils.argument
import java.io.File
import java.util.Date
import java.util.UUID
import kotlin.math.max
import kotlin.math.roundToInt



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
    private lateinit var binding: FragmentCrimeBinding
    private val crimeDetailViewModel by viewModels<CrimeDetailViewModel>()
    var uuid: UUID by argument()
    private var crime = Crime()
    private lateinit var photoFile: File
    private lateinit var photoUri: Uri

    private lateinit var applicationId: String

    private val contactNumberLauncher =
        registerForActivityResult(ActivityResultContracts.PickContact()) { uri ->
            if (uri == null) return@registerForActivityResult
            val queryFields = arrayOf(ContactsContract.Contacts.DISPLAY_NAME)
            val cursor = requireActivity().contentResolver.query(uri, queryFields, null, null, null)
            cursor?.apply {
                if (count == 0) return@registerForActivityResult
                moveToFirst()
                crime.suspect = cursor.getString(0)
                crimeDetailViewModel.saveCrime(crime)
                close()
            }
        }

    private val photoLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        updatePhotoView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        applicationId = requireContext().packageName
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

        }
        binding.crimeSuspect.setOnClickListener {
            contactNumberLauncher.launch(null)
        }
        binding.crimeCamera.setOnClickListener {
            Log.d(TAG, "onCreateView: " + photoUri.toString())
            photoLauncher.launch(photoUri)
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeDetailViewModel.crimeLiveData.observe(viewLifecycleOwner) { crime ->
            crime?.let {
                this.crime = crime
                photoFile = crimeDetailViewModel.getPhotoFile(crime)
                photoUri = FileProvider.getUriForFile(
                    requireActivity(),
                    "${applicationId}.MyFileProvider",
                    photoFile
                )
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
            if (crime.suspect.isNotEmpty()) {
                binding.crimeSuspect.text = crime.suspect
            }
        }
        updatePhotoView()
    }

    private fun updatePhotoView() {
        if (photoFile.exists()) {
            val bitmap = getScaledBitmap(photoFile.path, requireActivity())
            binding.crimePhoto.setImageBitmap(bitmap)
        } else {
            binding.crimePhoto.setImageBitmap(null)
        }
    }

    fun getScaledBitmap(path: String, activity: Activity): Bitmap {
        val point = ScreenUtils.getScreenMetrics(activity)
        return getScaledBitmap(path, point.x, point.y)
    }

    private fun getScaledBitmap(path: String, destWidth: Int, destHeight: Int): Bitmap {
        var options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, options)
        val srcWith = options.outWidth.toFloat()
        val srcHeight = options.outHeight.toFloat()

        var inSampleSize = 1
        if (srcHeight > destHeight || srcWith > destWidth) {
            val heightScale = srcHeight / destHeight
            val widthScale = srcWith / destWidth
            inSampleSize = max(heightScale, heightScale).roundToInt()
        }
        options = BitmapFactory.Options()
        options.inSampleSize = inSampleSize
        return BitmapFactory.decodeFile(path, options)
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

