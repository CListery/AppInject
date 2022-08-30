package com.yh.appinject

import android.os.Looper
import android.os.isCurrentLooper
import com.yh.appbasic.init.BasicInitializer

/**
 * 库注入协助器
 *
 * 1.库全局可访问Context
 *
 * 2.库独立控制日志输出
 *
 * 3.方便监听APP进入后台、前台
 *
 * 4.库全局可展示消息提示
 *
 * @constructor 默认无参构造
 *
 * Created by CYH on 2019-08-02 08:53
 */
open class InjectHelper<Inject : IBaseAppInject> : BasicInitializer() {
    
    /**
     * [Inject]
     */
    private var mInject: Inject? = null
    
    val inject: Inject get() = mInject!!
    
    /**
     * 注入并初始化库
     */
    open fun register(inject: Inject) {
        mInject = inject
        init()
    }
    
    /**
     * 可以在此处进行库的初始化工作
     */
    protected open fun init() {}
    
    /**
     * 显示提示信息
     *
     * @param [msg] 提示信息
     */
    fun showTipMsg(msg: String) {
        if(Looper.getMainLooper().isCurrentLooper) {
            internalShow(msg)
        } else {
            runOnUiThread({ internalShow(msg) })
        }
    }
    
    private fun internalShow(msg: String) {
        inject.showTipMsg(msg)
    }
}