package com.example.library_base.expand_fun

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

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
    val launcherPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
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