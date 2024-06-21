package com.example.library_base.expand_fun

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

/**
 *
 * 手机相关的操作
 * @author TXZ
 * @version 1.0
 * @date 2024/6/19 13:32
 */

/**
 * 获取客户的手机号码 与 名称
 *
 * @param function
 * @return
 */
fun ComponentActivity.getContacts(function: (ContactInfo) -> Unit): ActivityResultLauncher<Void?> {
    return registerForActivityResult(ActivityResultContracts.PickContact()) {
        val contactInfo = ContactInfo()
        it?.apply {
            val cursor = contentResolver.query(it, null, null, null, null)
            cursor?.apply {
                if (cursor.moveToFirst()) {
                    //获取客户信息
                    val contactName =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    contactInfo.contactName = contactName

                    //获取电话号码
                    val contactId =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val phone = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,
                        null, null
                    )
                    phone?.apply {
                        phone.moveToFirst()
                        val contactNumber =
                            phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        contactInfo.phoneNum = contactNumber
                        if (!phone.isClosed) {
                            phone.close()
                        }
                    }
                    if (!cursor.isClosed) {
                        cursor.close()
                    }
                }
            }
        }
        function(contactInfo)
    }
}

data class ContactInfo(var contactName: String = "", var phoneNum: String = "")

/**
 * 手机功能相关扩展方法：拨打电话
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/6/20 23:31
 */


/**
 * 直接拨打电话
 *
 * @param phoneNumber 电话号码
 */
fun ComponentActivity.callNumber(phoneNumber: String) {
    val launcherPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                val intent = Intent(Intent.ACTION_CALL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                }
                startActivity(intent)
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                    //第一次拒绝，显示提示文案
                } else {
                    //进入设置界面
                }
            }
        }
    launcherPermission.launch(Manifest.permission.CALL_PHONE)
}
