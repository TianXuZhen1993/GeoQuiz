package com.example.geoquiz.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

private const val TAG = "ActivityLifecycleCallBackImpl"

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/5/9 17:22
 */
class ActivityLifecycleCallBackImpl : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Log.i(TAG, "onActivityCreated: ")
    }

    override fun onActivityStarted(activity: Activity) {
        Log.i(TAG, "onActivityStarted: ")
    }

    override fun onActivityResumed(activity: Activity) {
        Log.i(TAG, "onActivityResumed: ")
    }

    override fun onActivityPaused(activity: Activity) {
        Log.i(TAG, "onActivityPaused: ")
    }

    override fun onActivityStopped(activity: Activity) {
        Log.i(TAG, "onActivityStopped: ")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Log.i(TAG, "onActivitySaveInstanceState: ")
    }

    override fun onActivityDestroyed(activity: Activity) {
        Log.i(TAG, "onActivityDestroyed: ")
    }
}