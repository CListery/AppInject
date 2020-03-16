package com.yh.appinject.lifecycle

import android.app.Activity

/**
 * Created by CYH on 2020-03-13 16:53
 */
interface IActStatusEvent : IAppForegroundEvent {
    
    fun onCreate(target: Activity)
    
    fun onShow(target: Activity)
    
    fun onHide(target: Activity)
    
    fun onDestroyed(target: Activity)
}