package com.example.geoquiz.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.library_base.expand_fun.toast

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/6/2 22:45
 */
class NormalBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        "收到广播".toast()
    }
}