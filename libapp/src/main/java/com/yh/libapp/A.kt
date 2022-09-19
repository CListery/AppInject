package com.yh.libapp

import com.yh.appbasic.initializer.AppBasicShare
import com.yh.appbasic.logger.logW
import com.yh.appbasic.logger.owner.LibLogger

/**
 * Created by CYH on 2020/5/16 22:33
 */
class A {
    
    init {
        logW("A init: ${Lib1.inject.lib1Number}", loggable = Lib1)
        logW("A init", loggable = AppBasicShare)
        logW("A init", loggable = LibLogger)
    }
}