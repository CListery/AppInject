package com.yh.libapp

import com.yh.appinject.logger.ext.libW

/**
 * Created by CYH on 2020/5/16 22:33
 */
class A {
    
    init {
        Lib1.get().libW("A init")
    }
}