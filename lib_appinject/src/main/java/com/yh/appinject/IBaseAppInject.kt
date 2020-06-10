package com.yh.appinject

import android.app.Application
import android.content.Context
import androidx.annotation.DrawableRes

/**
 * 基本的注入接口
 *
 * 可扩展一些自定义功能，可通过库的InjectHelper全局访问这些功能
 *
 * Created by CYH on 2019-08-01 16:30
 */
interface IBaseAppInject {

    /**
     * 获取APP上下文
     *
     * @return Application Context
     *
     * @see Context
     */
    fun getApplication(): Application

    /**
     * 显示提示信息
     *
     * @param [msg] 提示信息
     */
    fun showTipMsg(msg: String)

    /**
     * 获取通知图标资源id
     *
     * @return 通知图片资源id
     */
    @DrawableRes
    fun getNotificationIcon(): Int
}