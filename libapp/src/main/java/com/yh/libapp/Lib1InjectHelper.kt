package com.yh.libapp

import com.yh.appinject.IBaseAppInject
import com.yh.appinject.InjectHelper
import com.yh.appinject.lifecycle.IAppForegroundEvent

/**
 * Created by CYH on 2020-03-13 14:16
 */
abstract class Lib1InjectHelper<Inject : IBaseAppInject> : InjectHelper<Inject>() {

    private val mForegroundEvent by lazy {
        object : IAppForegroundEvent {
            override fun onForegroundStateChange(isForeground: Boolean) {
                if (isForeground) {
                    showTipMsg("App enter FG")
                } else {
                    showTipMsg("App enter BG")
                }
            }
        }
    }

    override fun register(inject: Inject) {
        super.register(inject)
        registerActivityLifecycleCallbacks(mForegroundEvent)
    }

}