package com.yh.appinject.ext

import android.util.Log
import com.yh.appinject.BuildConfig

/**
 * Created by CYH on 2020-01-15 16:58
 */

fun LogD(tag: String, msg: Any) {
    if (BuildConfig.DEBUG) {
        Log.d(tag, msg.toString())
    }
}

fun LogW(tag: String, msg: Any) {
    if (BuildConfig.DEBUG) {
        Log.w(tag, msg.toString())
    }
}

fun LogE(tag: String, msg: Any, throws: Throwable? = null) {
    if (BuildConfig.DEBUG) {
        if (null == throws) {
            Log.e(tag, msg.toString())
        } else {
            Log.e(tag, msg.toString(), throws)
        }
    }
}