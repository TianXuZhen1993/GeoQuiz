package com.example.library_base.utils

import android.Manifest.permission.CALL_PHONE
import android.Manifest.permission.SEND_SMS
import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.ContactsContract
import android.provider.Settings
import android.telephony.SmsManager
import android.telephony.SubscriptionManager
import androidx.annotation.RequiresPermission


/**
 *
 * Intent工具类，拨打电话、发送短信、权限设置、定位开启
 *
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/31 21:56
 */
object IntentUtils {
    /**
     *
     * @see getLocationSettingIntent 打开定位权限开启界面
     *
     * 打开应用的权限设置界面
     * @see getPermissionSettingIntent
     *
     * 直接发送短信
     * @see sendSMS
     * 发送短信
     * @see getSMSSendIntent
     *
     * 拨打电话
     * @see getDIALIntent
     * @see getCallIntent
     *
     * 获取联系人
     * @see getContactIntent
     */

    @JvmStatic
    fun getContactIntent(): Intent {
        return Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
    }

    /**
     * 打开定位权限开启界面
     *
     * @return
     */
    @JvmStatic
    fun getLocationSettingIntent(): Intent {
        return Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).apply {
            getNewTaskIntent(this, true)
        }
    }

    /**
     * 打开应用的权限设置界面
     *
     * @param packName
     * @return
     */
    @JvmStatic
    fun getPermissionSettingIntent(packName: String): Intent {
        return Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.parse("package:$packName")
            getNewTaskIntent(this, true)
        }
    }


    /**
     * 进入发送短信界面，附带信息
     *
     * @param phoneNumber
     * @param message
     * @return
     */
    @JvmStatic
    fun getSMSSendIntent(phoneNumber: String, message: String): Intent {
        return Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("sms:$phoneNumber")
            putExtra("sms_body", message)
            getNewTaskIntent(this, true)
        }
    }


    /**
     * 直接发送短信
     *
     * @param application
     * @param phoneNumber
     * @param message
     */
    @RequiresPermission(SEND_SMS)
    fun sendSMS(application: Application, phoneNumber: String, message: String) {
        val smsManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val defaultSmsSubscriptionId = SubscriptionManager.getDefaultSmsSubscriptionId()
            application.getSystemService(SmsManager::class.java).createForSubscriptionId(defaultSmsSubscriptionId)
        } else {
            application.getSystemService(SmsManager::class.java)
        }
        smsManager.sendTextMessage(phoneNumber, null, message, null, null)
    }


    /**
     * 进入拨打电话界面
     *
     * @param phoneNumber
     * @return
     */
    @JvmStatic
    fun getDIALIntent(phoneNumber: String): Intent {
        return Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
            getNewTaskIntent(this, true)
        }
    }


    /**
     * 直接拨打电话
     *
     * @param phoneNumber  手机号
     *  <uses-permission android:name="android.permission.CALL_PHONE" />
     *  <uses-feature android:name="android.hardware.telephony" android:required="false" />
     * @return
     */
    @RequiresPermission(CALL_PHONE)
    @JvmStatic
    fun getCallIntent(phoneNumber: String): Intent {
        return Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel:$phoneNumber")
            getNewTaskIntent(this, true)
        }
    }

    /**
     * Intent 增加NewTask
     *
     * @param intent
     * @param isNewTask
     * @return
     */
    private fun getNewTaskIntent(intent: Intent, isNewTask: Boolean): Intent {
        return if (isNewTask) intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) else intent
    }

}