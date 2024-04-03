package com.example.geoquiz.contract

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/4/3 10:13
 */
class MyActivityContract : ActivityResultContract<Intent, ActivityResult>() {

    override fun createIntent(context: Context, input: Intent): Intent {
        Log.d(TAG, "createIntent: createIntent")
        return input
    }

    override fun parseResult(
        resultCode: Int,
        intent: Intent?
    ): ActivityResult {
        Log.d(TAG, "parseResult: parseResult")
        return ActivityResult(resultCode, intent)
    }

    override fun getSynchronousResult(context: Context, input: Intent): SynchronousResult<ActivityResult>? {
        Log.d(TAG, "getSynchronousResult: ")
        return SynchronousResult(ActivityResult(0,Intent()))
    }
}

private const val TAG = "MyActivityContract"