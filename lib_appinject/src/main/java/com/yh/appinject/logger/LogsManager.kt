package com.yh.appinject.logger

import com.yh.appinject.InjectHelper
import com.yh.appinject.logger.impl.TheLogAdapter
import com.yh.appinject.logger.impl.TheLogFormatStrategy
import com.yh.appinject.logger.impl.TheLogPrinter

/**
 * Created by CYH on 2020/5/14 13:44
 */
class LogsManager private constructor() {
    
    companion object {
        @JvmStatic
        private val mInstances by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { LogsManager() }
        
        @JvmStatic
        fun get() = mInstances
    }
    
    private val mPrinter = TheLogPrinter()
    private val mMap: HashMap<InjectHelper<*>, LogAdapter> = hashMapOf()
    private val mDefaultLibAdapter by lazy {
        TheLogAdapter(TheLogFormatStrategy.newBuilder().setFirstTag("Library").build())
    }
    private val mDefaultAppAdapter by lazy {
        TheLogAdapter(TheLogFormatStrategy.newBuilder().setFirstTag("APP").build())
    }
    
    /**
     * @param libConfig Lib default logger config
     * @param appConfig App default logger config
     */
    fun setDefLoggerConfig(libConfig: Pair<Boolean, Int>? = null, appConfig: Pair<Boolean, Int>? = null) {
        if(null != libConfig) {
            mDefaultLibAdapter.setConfig(libConfig)
        }
        if(null != appConfig) {
            mDefaultAppAdapter.setConfig(appConfig)
        }
    }
    
    internal fun install(inject: InjectHelper<*>, adapter: LogAdapter) {
        mMap[inject] = adapter
    }
    
    internal fun uninstall(inject: InjectHelper<*>) {
        mMap.remove(inject)
    }
    
    internal fun with(adapter: LogAdapter?): Printer {
        if(null == adapter) {
            return mPrinter.adapter(mDefaultLibAdapter)
        }
        return mPrinter.adapter(adapter)
    }
    
    internal fun with(inject: InjectHelper<*>?): Printer? {
        if(null == inject) {
            return mPrinter.adapter(mDefaultAppAdapter)
        }
        if(mMap.contains(inject)) {
            return mPrinter.adapter(mMap[inject])
        }
        return null
    }
}