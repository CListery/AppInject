package com.yh.libapp

import com.yh.appbasic.init.AppBasicShare
import com.yh.appbasic.init.BasicInitializer
import com.yh.appbasic.logger.logW
import com.yh.appbasic.logger.owner.LibLogger

/**
 * Created by CYH on 2020/5/16 22:33
 */
class A {
    
    init {
        val lib1 = AppBasicShare.get<Lib1>()
        logW("A init: ${lib1?.inject?.lib1Number}", loggable = lib1)
        val basicInitializer = AppBasicShare.get<BasicInitializer>()
        logW("A init", loggable = basicInitializer)
        logW("A init", loggable = LibLogger)
    }
}