package com.yh.appinject.lifecycle

/**
 * Created by CYH on 2020-03-13 11:17
 */
interface IAppForegroundEvent {
    
    fun onForegroundStateChange(isForeground: Boolean) {}
}