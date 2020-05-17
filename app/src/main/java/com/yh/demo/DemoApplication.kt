package com.yh.demo

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.yh.appinject.IBaseAppInject
import com.yh.appinject.ext.isMainProcess
import com.yh.appinject.logger.LogsManager
import com.yh.libapp.Lib1

/**
 * Created by CYH on 2020-03-13 14:10
 */
class DemoApplication : Application(),
                        IBaseAppInject {
    
    private var sApp: DemoApplication? = null
    private var mCtx: Application? = null
    
    override fun getApplication(): Application {
        return sApp!!
    }
    
    override fun showTipMsg(msg: String) {
        Toast.makeText(mCtx, msg, Toast.LENGTH_SHORT).show()
    }
    
    override fun getNotificationIcon(): Int = R.mipmap.ic_launcher
    
    private fun libDefLoggerConfig(): Pair<Boolean, Int> = Pair(false, Log.VERBOSE)
    private fun appDefLoggerConfig(): Pair<Boolean, Int> = Pair(false, Log.VERBOSE)
    
    override fun onCreate() {
        super.onCreate()
        
        if(!isMainProcess()) {
            return
        }
        
        sApp = this
        mCtx = getApplication()
        
        LogsManager.get().setDefLoggerConfig(libDefLoggerConfig(), appDefLoggerConfig())
        Lib1.get().apply {
            loggerConfig(true to Log.VERBOSE)
            register(this@DemoApplication)
        }
    }
}