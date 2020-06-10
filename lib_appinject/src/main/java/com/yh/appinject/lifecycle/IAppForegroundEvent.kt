package com.yh.appinject.lifecycle

/**
 * 前/后台状态监听器
 *
 * Created by CYH on 2020-03-13 11:17
 */
interface IAppForegroundEvent {

    /**
     * 当APP前/后台状态发生变化时将会调用
     * @param isForeground true，APP进入前台
     */
    fun onForegroundStateChange(isForeground: Boolean) {}
}