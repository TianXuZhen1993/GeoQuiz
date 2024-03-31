package com.example.geoquiz.utils

import android.content.Intent
import android.net.Uri


/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/3/31 21:56
 */
object IntentUtils {


    /*
    电话拨打功能Intent 权限
    <uses-permission android:name="android.permission.CALL_PHONE" />
     <uses-feature android:name="android.hardware.telephony" android:required="false" />
    */

    /**
     * 进入拨打电话界面
     *
     * @param phoneNumber
     * @return
     */
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
     * @return
     */
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