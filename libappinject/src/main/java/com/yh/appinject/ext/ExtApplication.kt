package com.yh.appinject.ext

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Process

/**
 * Created by CYH on 2020-03-13 14:12
 */

fun Application.isMainProcess(context: Context): Boolean {
    val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val mainProcessName = context.packageName
    val myPid = Process.myPid()
    val processes = am.runningAppProcesses
    if (processes == null) {
        LogW("App", "isMainProcess get getRunningAppProcesses null")
        val processList = am.getRunningServices(Int.MAX_VALUE)
        if (processList == null) {
            LogW("APP", "isMainProcess get getRunningServices null")
            return false
        } else {
            processList.forEach { rsi ->
                if (rsi.pid == myPid && mainProcessName == rsi.service.packageName) {
                    return true
                }
            }
            return false
        }
    } else {
        processes.forEach { rapi ->
            if (rapi.pid == myPid && mainProcessName == rapi.processName) {
                return true
            }
        }
        return false
    }
}
