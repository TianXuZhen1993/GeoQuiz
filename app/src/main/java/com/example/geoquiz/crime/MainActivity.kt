package com.example.geoquiz.crime

import android.content.res.Resources
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.commit
import com.example.geoquiz.R
import com.example.geoquiz.databinding.ActivityMainBinding
import com.example.geoquiz.crime.fragment.CrimeFragment
import com.example.geoquiz.crime.fragment.CrimeListFragment
import com.example.geoquiz.dialog.InformDialog
import com.example.geoquiz.utils.ScreenUtils
import com.example.geoquiz.utils.SizeUtils
import com.example.geoquiz.utils.inflateBinding
import com.example.geoquiz.utils.toast
import java.util.UUID

private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by inflateBinding()

    companion object {
        const val MESSAGE = "Message"
    }

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                pickContact.launch(null)
            }
        }

    private val activityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.also { intent ->

                }
            }
        }

    private val pickContact = registerForActivityResult(ActivityResultContracts.PickContact()) { uri ->
        uri?.apply {
            val queryFields = arrayOf(ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts._ID)
            val cursor = contentResolver.query(uri, queryFields, null, null, null)
            cursor?.apply {
                moveToFirst()
                val columnIndex = getColumnIndex(ContactsContract.Contacts._ID)
                val contactId = getString(columnIndex)
                val contactCursor = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,
                    null,
                    null
                )
                contactCursor?.apply {
                    if (contactCursor.count > 0) {
                        contactCursor.moveToFirst()
                        val columnNumberIndex =
                            contactCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        contactCursor.getString(columnNumberIndex).toast()
                    }
                }
                contactCursor?.close()
            }
            cursor?.close()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: ")
        setContentView(binding.root)
        initView()
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    private fun initView() {
        binding.btnCommonDialog.setOnClickListener {
            InformDialog.Build().setTitle("提示内容")
                .setContent("账号过账，重新确认")
                .setSureText("我知道了")
                .create().show(supportFragmentManager)
        }
        SizeUtils.logScreenSize()
    }
}