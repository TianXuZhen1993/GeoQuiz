package com.example.geoquiz.crime

import android.content.res.Resources
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.geoquiz.databinding.ActivityMainBinding
import com.example.geoquiz.utils.toast
import com.example.library_base.base.BaseActivity
import com.example.library_base.utils.Logger
import com.example.library_base.utils.inflateBinding

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
        Logger.d("1111","2222")
        binding.btnCommonDialog.setOnClickListener {
//            SizeUtils.logScreenSize()
            Resources.getSystem().displayMetrics.apply {
                val message = "屏幕宽度px：${widthPixels} \n" +
                        "屏幕高度px：${heightPixels} \n" +
                        "屏幕densityDpi：${densityDpi} \n" +
                        "屏幕density：${density} \n" +
                        "屏幕scaledDensity:${scaledDensity}" +
                        "---------------------------------------------------- \n" +
                        "屏幕宽度dp：${widthPixels / density} \n" +
                        "屏幕高度dp:${heightPixels / density}"

               Logger.d(message)

            }

        }

    }
}