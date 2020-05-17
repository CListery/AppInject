package com.yh.appinject

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Process
import com.yh.appinject.ext.isMainProcess
import com.yh.appinject.lifecycle.ActLifeCallback
import com.yh.appinject.lifecycle.IAppForegroundEvent
import com.yh.appinject.logger.LogAdapter
import com.yh.appinject.logger.LogsManager
import com.yh.appinject.logger.impl.TheLogAdapter
import com.yh.appinject.logger.impl.TheLogFormatStrategy

/**
 * Created by CYH on 2019-08-02 08:53
 */
abstract class InjectHelper<Inject : IBaseAppInject> protected constructor() {
    
    private lateinit var mInject: Inject
    protected var mProcessID: Int = -1
    
    fun getInject() = mInject
    
    fun ctx(): Context = mInject.getApplication().applicationContext
    
    open fun register(inject: Inject) {
        mInject = inject
        if(inject.getApplication().isMainProcess()) {
            mProcessID = Process.myPid()
            ActLifeCallback.get().register(inject.getApplication())
        }
        init()
    }
    
    protected open fun init() {}
    
    fun loggerConfig(config: Pair<Boolean, Int>) {
        if(config.first) {
            LogsManager.get().install(this, makeLogAdapter(config))
        } else {
            LogsManager.get().uninstall(this)
        }
    }
    
    open fun logTag(): String = this::class.java.simpleName
    open fun makeLogAdapter(config: Pair<Boolean, Int>): LogAdapter = TheLogAdapter(makeFormatStrategy().build()).setConfig(config)
    open fun makeFormatStrategy(): TheLogFormatStrategy.Builder = TheLogFormatStrategy.newBuilder().setFirstTag(logTag())
    
    fun registerActivityLifecycleCallbacks(iAppForegroundEvent: IAppForegroundEvent) {
        ActLifeCallback.get().pushListener(iAppForegroundEvent)
    }
    
    fun unRegisterActivityLifecycleCallbacks(iAppForegroundEvent: IAppForegroundEvent) {
        ActLifeCallback.get().popListener(iAppForegroundEvent)
    }
    
    fun isForeground() = ActLifeCallback.get().isForeground()
    
    fun showTipMsg(msg: String) {
        if(Thread.currentThread() == Looper.getMainLooper().thread) {
            internalShow(msg)
        } else {
            Handler(ctx().mainLooper).post { internalShow(msg) }
        }
    }
    
    private fun internalShow(msg: String) {
        getInject().showTipMsg(msg)
    }
}