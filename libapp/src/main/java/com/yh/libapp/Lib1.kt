package com.yh.libapp

import com.yh.appinject.IBaseAppInject

/**
 * Created by CYH on 2020-03-13 14:07
 */
class Lib1 private constructor() : Lib1InjectHelper<IBaseAppInject>() {

    companion object {
        @JvmStatic
        private val mInstances by lazy { Lib1() }

        @JvmStatic
        fun get() = mInstances
    }

}