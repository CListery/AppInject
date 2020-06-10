package com.yh.appinject.ext

import android.app.ActivityManager
import android.app.Application
import android.os.Process
import androidx.core.content.getSystemService
import com.yh.appinject.logger.LibLogs

/**
 * [android.app.Application]扩展
 *
 * Created by CYH on 2020-03-13 14:12
 */

/**
 * 当前是否处于主进程
 *
 * 某些情况下APP会被拉起，这些情况下并非处于主进程状态，这些时候有些操作应该被触发，比如某些第三方的库初始化等
 *
 * @return true，当前处于主进程
 */
fun Application.isMainProcess(): Boolean {
    val am: ActivityManager = getSystemService()
        ?: return false
    val mainProcessName = packageName
    val myPid = Process.myPid()
    val processes = try {
        am.runningAppProcesses
    } catch (e: Exception) {
        null
    }
    if (null == processes || processes.isEmpty()) {
        LibLogs.logW("isMainProcess get getRunningAppProcesses empty", "App")
        val processList = try {
            am.getRunningServices(Int.MAX_VALUE)
        } catch (e: Exception) {
            null
        }
        if (null == processList || processList.isEmpty()) {
            LibLogs.logW("isMainProcess get getRunningServices empty", "App")
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
