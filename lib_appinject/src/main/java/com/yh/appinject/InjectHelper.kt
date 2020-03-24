/*
 * Copyright 2019 FangStar, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yh.appinject

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Process
import com.yh.appinject.ext.isMainProcess
import com.yh.appinject.lifecycle.ActLifeCallback
import com.yh.appinject.lifecycle.IAppForegroundEvent

/**
 * Created by CYH on 2019-08-02 08:53
 */
abstract class InjectHelper<Inject : IBaseAppInject> protected constructor() {
    
    companion object {
        private const val TAG = "InjectHelper"
    }
    
    private lateinit var mInject: Inject
    protected var mProcessID: Int = -1
    
    fun getInject() = mInject
    
    fun ctx(): Context = mInject.getApplication().applicationContext
    
    open fun register(inject: Inject) {
        mInject = inject
        if (inject.getApplication().isMainProcess()) {
            mProcessID = Process.myPid()
            ActLifeCallback.get().register(inject.getApplication())
        }
        init()
    }
    
    protected open fun init() {
    
    }
    
    fun registerActivityLifecycleCallbacks(iAppForegroundEvent: IAppForegroundEvent){
        ActLifeCallback.get().pushListener(iAppForegroundEvent)
    }
    
    fun unRegisterActivityLifecycleCallbacks(iAppForegroundEvent: IAppForegroundEvent){
        ActLifeCallback.get().popListener(iAppForegroundEvent)
    }
    
    fun isForeground() = ActLifeCallback.get().isForeground()
    
    fun showTipMsg(msg: String) {
        if (Thread.currentThread() == Looper.getMainLooper().thread) {
            internalShow(msg)
        } else {
            Handler(ctx().mainLooper).post { internalShow(msg) }
        }
    }
    
    private fun internalShow(msg: String) {
        getInject().showTipMsg(msg)
    }
    
}