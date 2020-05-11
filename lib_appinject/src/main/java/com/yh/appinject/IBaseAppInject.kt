package com.yh.appinject

import android.app.Application
import androidx.annotation.DrawableRes

/**
 * Created by CYH on 2019-08-01 16:30
 */
interface IBaseAppInject {
    
    fun getApplication(): Application
    fun showTipMsg(errorMsg: String)
    @DrawableRes
    fun getNotificationIcon(): Int
}