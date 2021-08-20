package com.yh.libapp

import com.yh.appinject.IBaseAppInject
import com.yh.appinject.InjectHelper
import com.yh.appinject.logger.impl.TheLogFormatStrategy

/**
 * Created by CYH on 2020-03-13 14:16
 */
abstract class Lib1InjectHelper<Inject : IBaseAppInject> : InjectHelper<Inject>() {
    
    override fun makeFormatStrategy(): TheLogFormatStrategy.Builder {
        return super.makeFormatStrategy().setMethodCount(5)
    }
}