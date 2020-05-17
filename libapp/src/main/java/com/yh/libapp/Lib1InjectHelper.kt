package com.yh.libapp

import com.yh.appinject.IBaseAppInject
import com.yh.appinject.InjectHelper
import com.yh.appinject.lifecycle.IAppForegroundEvent
import com.yh.appinject.logger.ext.libW
import com.yh.appinject.logger.impl.TheLogFormatStrategy

/**
 * Created by CYH on 2020-03-13 14:16
 */
abstract class Lib1InjectHelper<Inject : IBaseAppInject> : InjectHelper<Inject>() {
    
    private val mForegroundEvent by lazy {
        object : IAppForegroundEvent {
            override fun onForegroundStateChange(isForeground: Boolean) {
                if(isForeground) {
                    showTipMsg("App enter FG")
                    libW("App enter FG")
                } else {
                    showTipMsg("App enter BG")
                    libW("App enter BG")
                }
            }
        }
    }
    
    override fun register(inject: Inject) {
        super.register(inject)
        registerActivityLifecycleCallbacks(mForegroundEvent)
    }
    
    override fun makeFormatStrategy(): TheLogFormatStrategy.Builder {
        return super.makeFormatStrategy().setMethodCount(5)
    }
}