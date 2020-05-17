package com.yh.appinject

import android.app.Application
import androidx.annotation.DrawableRes

/**
 * Created by CYH on 2019-08-01 16:30
 */
interface IBaseAppInject {
    
    /**
     * APP context
     */
    fun getApplication(): Application
    
    /**
     * Show tip
     */
    fun showTipMsg(msg: String)
    
    /**
     * Notification icon
     */
    @DrawableRes
    fun getNotificationIcon(): Int
}