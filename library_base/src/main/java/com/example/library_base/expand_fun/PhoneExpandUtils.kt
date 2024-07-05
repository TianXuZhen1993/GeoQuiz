package com.example.library_base.expand_fun

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.provider.CallLog
import android.provider.ContactsContract
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.core.location.LocationManagerCompat
import androidx.fragment.app.FragmentActivity
import com.example.library_base.utils.TimeUtils

/**
 * 手机相关的操作
 * @author TXZ
 * @version 1.0
 * @date 2024/6/19 13:32
 *
 * @see locationServiceIsOpen  检查定位服务是否开启
 * @see getContactsLauncher  获取客户的手机号码 与 名称
 * @see getPhoneRecordDuration 获取手机号当天的最新的通话时长
 */



/**
 * 检查定位服务是否开启
 * @return
 */
fun ComponentActivity.locationServiceIsOpen(): Boolean {
    val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    // LocationManagerCompat 是辅助工具类，兼容之前的版本
    return LocationManagerCompat.isLocationEnabled(locationManager)
}


data class ContactInfo(var contactName: String = "", var phoneNum: String = "")
/**
 * 获取客户的手机号码 与 名称
 * 不能直接获取，registerForActivityResult只能在onStart之前执行
 * @param function
 * @return
 */
@SuppressLint("Range")
fun ComponentActivity.getContactsLauncher(function: (ContactInfo) -> Unit): ActivityResultLauncher<Void?> {
    return registerForActivityResult(ActivityResultContracts.PickContact()) {
        if (it == null) return@registerForActivityResult
        val contactInfo = ContactInfo()
        val cursor = contentResolver.query(it, null, null, null, null)
        cursor?.apply {
            if (cursor.moveToFirst()) {
                //获取客户信息
                val contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                contactInfo.contactName = contactName

                //获取电话号码
                val contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
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
        function(contactInfo)
    }
}


/**
 * 获取手机号当天的最新的通话时长
 * @param phoneNum
 */
@SuppressLint("Range")
@RequiresPermission(Manifest.permission.READ_CALL_LOG)
fun FragmentActivity.getPhoneRecordDuration(phoneNum: String): Int {
    var duration = 0
    val todayZero = TimeUtils.getTodayZeroTime().time
    val cursor = contentResolver.query(
        CallLog.Calls.CONTENT_URI, arrayOf(
            CallLog.Calls.NUMBER, CallLog.Calls.DATE,
            CallLog.Calls.DURATION, CallLog.Calls.TYPE
        ), null, null, CallLog.Calls.DEFAULT_SORT_ORDER
    )
    cursor?.let {
        while (cursor.moveToNext()) {
            val dateLong = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE))
            if (dateLong < todayZero) {
                //跳出while循环
                break
            }
            //以下是当天日期
            val number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER))
            val type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE))
            if (phoneNum.trim() == number && type == CallLog.Calls.OUTGOING_TYPE) {
                duration = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.DURATION))
                break
            }
        }
        cursor.close()
    }
    return duration
}
