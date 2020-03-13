package com.yh.appinject.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.yh.appinject.ext.LogW
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by CYH on 2019-12-19 13:37
 */
class ActLifeCallback private constructor() : Application.ActivityLifecycleCallbacks {
    
    companion object {
        private const val TAG = "ActLifeCallback"
        
        @JvmStatic
        private val mInstances: ActLifeCallback by lazy { ActLifeCallback() }
        
        @JvmStatic
        fun get() = mInstances
    }
    
    private val mIsRegistered = AtomicBoolean(false)
    private val mForegroundActCount = AtomicInteger(0)
    private val mIsChangingConfiguration = AtomicBoolean(false)
    
    private val mForegroundStatus = AtomicBoolean(false)
    
    private val mEventList = ArrayList<IAppForegroundEvent>()
    
    fun register(app: Application) {
        if (mIsRegistered.compareAndSet(false, true)) {
            app.registerActivityLifecycleCallbacks(this)
        }
    }
    
    fun isForeground() = mForegroundStatus.get()
    
    fun pushListener(iAppForegroundEvent: IAppForegroundEvent) {
        if (!mEventList.contains(iAppForegroundEvent)) {
            mEventList.add(iAppForegroundEvent)
        }
    }
    
    fun popListener(iAppForegroundEvent: IAppForegroundEvent) {
        mEventList.remove(iAppForegroundEvent)
    }
    
    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        LogW(TAG, "onActivityCreated act: $activity -> bundle: $bundle")
    }
    
    override fun onActivityStarted(activity: Activity) {
        LogW(TAG, "onActivityStarted: $activity")
        if (1 == mForegroundActCount.incrementAndGet() && !mIsChangingConfiguration.get()) {
            // 应用切到前台
            if (mForegroundStatus.compareAndSet(false, true)) {
                LogW(TAG, "enter FG: $activity")
                mEventList.toArray(arrayOf<IAppForegroundEvent>()).forEach { it.onForegroundStateChange(true) }
            }
        }
        mIsChangingConfiguration.set(false)
    }
    
    override fun onActivityResumed(activity: Activity) {
        LogW(TAG, "onActivityResumed: $activity")
    }
    
    override fun onActivityPaused(activity: Activity) {
        LogW(TAG, "onActivityPaused: $activity")
    }
    
    override fun onActivityStopped(activity: Activity) {
        LogW(TAG, "onActivityStopped: $activity")
        if (0 == mForegroundActCount.decrementAndGet()) {
            // 应用切到后台
            if (mForegroundStatus.compareAndSet(true, false)) {
                LogW(TAG, "enter BG: $activity")
                mEventList.toArray(arrayOf<IAppForegroundEvent>()).forEach { it.onForegroundStateChange(false) }
            }
        }
        mIsChangingConfiguration.set(activity.isChangingConfigurations)
    }
    
    override fun onActivityDestroyed(activity: Activity) {
        LogW(TAG, "onActivityDestroyed: $activity")
    }
    
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        LogW(TAG, "onActivitySaveInstanceState act: $activity -> bundle: $outState")
    }
}