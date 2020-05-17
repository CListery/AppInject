package com.yh.demo

import android.app.Activity
import android.util.Log
import com.yh.appinject.logger.LogsManager

/**
 * Created by CYH on 2020-03-13 14:36
 */
class SecAct : Activity() {
    
    override fun onDestroy() {
        LogsManager.get().setDefLoggerConfig(libConfig = Pair(true, Log.VERBOSE), appConfig = Pair(false, Log.VERBOSE))
        
        super.onDestroy()
    }
}