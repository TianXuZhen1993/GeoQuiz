package com.example.geoquiz.crime

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.blankj.utilcode.util.Utils
import com.example.geoquiz.databinding.ActivityMainBinding
import com.example.geoquiz.utils.toast
import com.example.library_base.base.BaseActivity
import com.example.library_base.utils.CoreUtils
import com.example.library_base.utils.inflateBinding
import com.example.library_base.utils.logD
import com.example.library_base.utils.toast

private const val TAG = "MainActivity"


class MainActivity : BaseActivity() {
    private val binding: ActivityMainBinding by inflateBinding()

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

    private val pickContact =
        registerForActivityResult(ActivityResultContracts.PickContact()) { uri ->
            uri?.apply {
                val queryFields =
                    arrayOf(ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts._ID)
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
           StringBuilder().append("tian").toast()
        }
    }
}