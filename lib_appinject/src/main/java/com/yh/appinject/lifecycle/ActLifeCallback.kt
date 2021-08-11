package com.yh.appinject.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.yh.appinject.ext.memoryId
import com.yh.appinject.logger.LibLogs

/**
 * Activity生命周期监听器
 *
 * Created by CYH on 2019-12-19 13:37
 */
internal class ActLifeCallback private constructor() : Application.ActivityLifecycleCallbacks {
    
    companion object {
        
        private const val TAG = "ActLifeCallback"
        
        @JvmStatic
        private val mInstances: ActLifeCallback by lazy { ActLifeCallback() }
        
        @JvmStatic
        fun get() = mInstances
    }
    
    private var mIsRegistered = false
    private var mInStackActCount = 0
    private var mIsChangingConfiguration = false
    private val mEventList = ArrayList<IAppForegroundEvent>()
    
    /**
     * 注册监听器
     *
     * @param app Application
     */
    fun register(app: Application) {
        if(!mIsRegistered) {
            mIsRegistered = true
            app.registerActivityLifecycleCallbacks(this)
        }
    }
    
    /**
     * APP是否处于前台
     * @return true，当前处于前台
     */
    fun isForeground() = mInStackActCount > 0
    
    /**
     * 注册前/后台状态监听器
     */
    fun pushListener(iAppForegroundEvent: IAppForegroundEvent) {
        if(!mEventList.contains(iAppForegroundEvent)) {
            mEventList.add(iAppForegroundEvent)
        }
    }
    
    /**
     * 注销前/后台状态监听器
     */
    fun popListener(iAppForegroundEvent: IAppForegroundEvent) {
        mEventList.remove(iAppForegroundEvent)
    }
    
    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        LibLogs.logD("onActivityCreated act: ${activity.tag} -> bundle: $bundle", TAG)
        mEventList.filterIsInstance<IActStatusEvent>().forEach {
            it.onCreate(activity)
        }
    }
    
    override fun onActivityStarted(activity: Activity) {
        LibLogs.logD("onActivityStarted: ${activity.tag}".plus(if(mIsChangingConfiguration) " - changing configurations" else ""), TAG)
        if(mIsChangingConfiguration) {
            mIsChangingConfiguration = false
            return
        }
        if(++mInStackActCount == 1) {
            LibLogs.logD("enter FG: ${activity.tag}", TAG)
            mEventList.toArray(arrayOf<IAppForegroundEvent>())
                .forEach { it.onForegroundStateChange(true) }
        }
    }
    
    override fun onActivityResumed(activity: Activity) {
        LibLogs.logD("onActivityResumed: ${activity.tag}", TAG)
        mEventList.filterIsInstance<IActStatusEvent>().forEach {
            it.onShow(activity)
        }
    }
    
    override fun onActivityPaused(activity: Activity) {
        LibLogs.logD("onActivityPaused: ${activity.tag}", TAG)
        mEventList.filterIsInstance<IActStatusEvent>().forEach {
            it.onHide(activity)
        }
    }
    
    override fun onActivityStopped(activity: Activity) {
        LibLogs.logD("onActivityStopped: ${activity.tag}".plus(if(activity.isChangingConfigurations) " - changing configurations" else ""), TAG)
        if(activity.isChangingConfigurations) {
            mIsChangingConfiguration = true
            return
        }
        if(--mInStackActCount == 0) {
            LibLogs.logD("enter BG: ${activity.tag}", TAG)
            mEventList.toArray(arrayOf<IAppForegroundEvent>())
                .forEach { it.onForegroundStateChange(false) }
        }
    }
    
    override fun onActivityDestroyed(activity: Activity) {
        LibLogs.logD("onActivityDestroyed: ${activity.tag}", TAG)
        mEventList.filterIsInstance<IActStatusEvent>().forEach {
            it.onDestroyed(activity)
        }
    }
    
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        LibLogs.logD(
            "onActivitySaveInstanceState act: ${activity.tag} -> bundle: $outState",
            TAG
        )
    }
}

internal val Activity.tag get() = this::class.java.simpleName.plus("[${this.memoryId}]")
