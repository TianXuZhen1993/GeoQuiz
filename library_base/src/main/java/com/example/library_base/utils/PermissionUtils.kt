package com.example.library_base.utils

import android.provider.ContactsContract
import androidx.activity.result.contract.ActivityResultContracts

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/5/22 15:56
 */
@Deprecated("调试阶段")
class PermissionUtils {
//    private val pickContact = registerForActivityResult(ActivityResultContracts.PickContact()) { uri ->
//        uri?.apply {
//            val queryFields =
//                arrayOf(ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts._ID)
//            val cursor = contentResolver.query(uri, queryFields, null, null, null)
//            cursor?.apply {
//                moveToFirst()
//                val columnIndex = getColumnIndex(ContactsContract.Contacts._ID)
//                val contactId = getString(columnIndex)
//                val contactCursor = contentResolver.query(
//                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                    null,
//                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,
//                    null,
//                    null
//                )
//                contactCursor?.apply {
//                    if (contactCursor.count > 0) {
//                        contactCursor.moveToFirst()
//                        val columnNumberIndex =
//                            contactCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
//                        contactCursor.getString(columnNumberIndex).toast()
//                    }
//                }
//                contactCursor?.close()
//            }
//            cursor?.close()
//        }
//    }

}