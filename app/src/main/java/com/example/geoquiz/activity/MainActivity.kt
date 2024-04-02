package com.example.geoquiz.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.geoquiz.R
import com.example.geoquiz.databinding.ActivityMainBinding
import com.example.geoquiz.fragment.CrimeFragment
import com.example.geoquiz.fragment.CrimeListFragment
import com.example.geoquiz.utils.inflateBinding
import com.example.geoquiz.utils.toast
import java.util.UUID

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), CrimeListFragment.Callbacks {
    private val binding: ActivityMainBinding by inflateBinding()

    companion object {
        const val MESSAGE = "Message"
    }

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                pickContact.launch(null)
                LogUtils.d("获取权限成功")
            }
        }

    private val activityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { it ->
            if (it.resultCode == RESULT_OK) {
                it.data?.also { intent ->
                    ToastUtils.showShort(intent.getStringExtra(MESSAGE))
                }
            }
        }

    private val pickContact = registerForActivityResult(ActivityResultContracts.PickContact()) { uri ->
            uri?.apply {
                val queryFields = arrayOf(
                    ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts._ID
                )
                val cursor = contentResolver.query(uri, null, null, null, null)
                cursor?.apply {
                    moveToFirst()
                    val columnIndex = getColumnIndex(ContactsContract.Contacts._ID)
                    val contactId = getString(columnIndex)
                    val contactCursor = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone._ID + "=" + contactId,
                        null,
                        null
                    )
                    contactCursor?.apply {
                        if (contactCursor.count > 0) {
                            contactCursor.moveToFirst()
                            val columnNumberIndex = contactCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
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
        initFragment()
        initView()
    }

    private fun initView() {
        binding.button.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_CONTACTS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionLauncher.launch(Manifest.permission.READ_CONTACTS)
            } else {
                pickContact.launch(null)
            }
        }
    }

    private fun initFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            val fragment = CrimeListFragment.newInstance()
            supportFragmentManager.commit {
                add(R.id.fragment_container, fragment)
            }
        }
    }

    override fun onCrimeSelected(crimeId: UUID) {
        val fragment = CrimeFragment()
        fragment.uuid = crimeId
        supportFragmentManager.commit {
            replace(R.id.fragment_container, fragment).addToBackStack(null)
        }
    }
}