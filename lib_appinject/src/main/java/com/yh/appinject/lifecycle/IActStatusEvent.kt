package com.yh.appinject.lifecycle

import android.app.Activity

/**
 * Activity状态监听器
 *
 * Created by CYH on 2020-03-13 16:53
 */
interface IActStatusEvent : IAppForegroundEvent {

    /**
     * @see Activity.onCreate
     */
    fun onCreate(target: Activity)

    /**
     * @see Activity.onResume
     */
    fun onShow(target: Activity)

    /**
     * @see Activity.onPause
     */
    fun onHide(target: Activity)

    /**
     * @see Activity.onDestroy
     */
    fun onDestroyed(target: Activity)
}