package com.yh.appinject.ext

import android.app.ActivityManager
import android.app.Application
import android.os.Process
import androidx.core.content.getSystemService

/**
 * Created by CYH on 2020-03-13 14:12
 */
fun Application.isMainProcess(): Boolean {
    val am: ActivityManager = getSystemService() ?: return false
    val mainProcessName = packageName
    val myPid = Process.myPid()
    val processes = try {
        am.runningAppProcesses
    } catch (e: Exception) {
        null
    }
    if (null == processes || processes.isEmpty()) {
        LogW("App", "isMainProcess get getRunningAppProcesses empty")
        val processList = try {
            am.getRunningServices(Int.MAX_VALUE)
        } catch (e: Exception) {
            null
        }
        if (null == processList || processList.isEmpty()) {
            LogW("APP", "isMainProcess get getRunningServices empty")
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
