package com.example.library_base.utils

import android.Manifest.permission.CALL_PHONE
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.annotation.RequiresPermission
import com.example.library_base.core.CoreUtils
import com.example.library_base.utils.IntentUtils.getCallIntent
import com.example.library_base.utils.IntentUtils.getDIALIntent
import com.example.library_base.utils.IntentUtils.getLocationSettingIntent
import com.example.library_base.utils.IntentUtils.getPermissionSettingIntent
import com.example.library_base.utils.IntentUtils.getSMSSendIntent


/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/31 21:56
 *
 * @see getLocationSettingIntent 打开定位权限开启界面
 * @see getPermissionSettingIntent  打开应用的权限设置界面
 * @see getCallIntent 直接拨打电话，需要电话权限
 * @see getDIALIntent 进入拨打电话界面
 * @see getSMSSendIntent 进入短信拨号界面
 */
object IntentUtils {

    /**
     * 打开定位权限开启界面
     *
     * @return
     */
    @JvmStatic
    fun getLocationSettingIntent(): Intent {
        return Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
    }


    /**
     * 打开应用的权限设置界面
     *
     * @param packName
     * @return
     */
    @JvmStatic
    fun getPermissionSettingIntent(packName: String = CoreUtils.getApp().packageName): Intent {
        return Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.parse("package:$packName")
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
        }
    }

    /**
     * 进入拨打电话界面,无需权限
     *
     * @param phoneNumber
     * @return
     */
    @JvmStatic
    fun getDIALIntent(phoneNumber: String): Intent {
        return Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
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
    fun getSMSSendIntent(phoneNumber: String, message: String = ""): Intent {
        return Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("smsto:$phoneNumber")
            putExtra("sms_body", message)
        }
    }
}