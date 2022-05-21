package com.yh.appinject

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Process
import com.yh.appbasic.ext.isCurrentLooper
import com.yh.appbasic.logger.ILogOwner
import com.yh.appbasic.logger.LogsManager

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
abstract class InjectHelper<Inject : IBaseAppInject> protected constructor() : ILogOwner {

    /**
     * [Inject]
     */
    private lateinit var mInject: Inject
    /**
     * Lib 初始化时的 PID
     */
    @Volatile
    protected var mProcessID: Int = -1

    /**
     * 获取注入实例对象
     *
     * @return [Inject]
     * @see Inject
     */
    fun getInject() = mInject

    /**
     * Application Context
     *
     * @see Context
     */
    fun ctx(): Context = mInject.getApplication().applicationContext

    /**
     * 注入并初始化库
     */
    open fun register(inject: Inject) {
        mInject = inject
        mProcessID = Process.myPid()
        init()
    }

    /**
     * 可以在此处进行库的初始化工作
     */
    protected open fun init() {}

    /**
     * 设置日志等级及开关
     *
     * @param [config]
     *  - first - 是否开启日志
     *  - second - 日志等级 [android.util.Log]
     */
    override fun loggerConfig(config: Pair<Boolean, Int>) {
        if (config.first) {
            LogsManager.get().install(this, makeLogAdapter(config))
        } else {
            LogsManager.get().uninstall(this)
        }
    }

    // /**
    //  * 日志TAG
    //  *
    //  * @return 默认为该类的类名
    //  */
    // open fun logTag(): String = this::class.java.simpleName

    /**
     * 显示提示信息
     *
     * @param [msg] 提示信息
     */
    fun showTipMsg(msg: String) {
        if (Looper.getMainLooper().isCurrentLooper) {
            internalShow(msg)
        } else {
            Handler(ctx().mainLooper).post { internalShow(msg) }
        }
    }

    private fun internalShow(msg: String) {
        getInject().showTipMsg(msg)
    }
}