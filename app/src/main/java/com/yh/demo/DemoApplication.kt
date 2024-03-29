package com.yh.demo

import android.app.Application
import android.content.isMainProcess
import android.widget.Toast
import com.yh.appbasic.logger.logE
import com.yh.appbasic.logger.owner.AppLogger
import com.yh.appbasic.logger.owner.LibLogger
import com.yh.appbasic.share.AppBasicShare
import com.yh.appinject.IBaseAppInject
import com.yh.libapp.Lib1
import com.yh.libapp.Lib1Inject

/**
 * Created by CYH on 2020-03-13 14:10
 */
class DemoApplication : Application() {
    
    private var sApp: DemoApplication? = null
    private var mCtx: Application? = null
    
    private inner class InjectImpl : IBaseAppInject, Lib1Inject {
        
        override val lib1Number: Number get() = Math.random()
        
        override fun showTipMsg(msg: String) {
            Toast.makeText(mCtx, msg, Toast.LENGTH_SHORT).show()
        }
        
        override fun getNotificationIcon(): Int = R.mipmap.ic_launcher
    }
    
    override fun onCreate() {
        super.onCreate()
    
        AppBasicShare.install(this)
        
        if(!isMainProcess()) {
            return
        }
        
        logE("onCreate")
        
        sApp = this
        mCtx = this
        
        AppLogger.off()
        LibLogger.off()
        
        Lib1.register(InjectImpl())
    }
}