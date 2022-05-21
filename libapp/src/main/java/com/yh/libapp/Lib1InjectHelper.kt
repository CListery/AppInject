package com.yh.libapp

import com.yh.appbasic.logger.impl.TheLogFormatStrategy
import com.yh.appinject.IBaseAppInject
import com.yh.appinject.InjectHelper

/**
 * Created by CYH on 2020-03-13 14:16
 */
abstract class Lib1InjectHelper<Inject : IBaseAppInject> : InjectHelper<Inject>() {
    
    override fun makeFormatStrategy(): TheLogFormatStrategy.Builder {
        return super.makeFormatStrategy().setMethodCount(5)
    }
}