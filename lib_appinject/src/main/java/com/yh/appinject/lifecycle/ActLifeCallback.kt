package com.yh.appinject.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.yh.appinject.logger.LibLogs
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

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

    private val mIsRegistered = AtomicBoolean(false)
    private val mForegroundActCount = AtomicInteger(0)
    private val mIsChangingConfiguration = AtomicBoolean(false)
    private val mForegroundStatus = AtomicBoolean(false)
    private val mEventList = ArrayList<IAppForegroundEvent>()

    /**
     * 注册监听器
     *
     * @param app Application
     */
    fun register(app: Application) {
        if (mIsRegistered.compareAndSet(false, true)) {
            app.registerActivityLifecycleCallbacks(this)
        }
    }

    /**
     * APP是否处于前台
     * @return true，当前处于前台
     */
    fun isForeground() = mForegroundStatus.get()

    /**
     * 注册前/后台状态监听器
     */
    fun pushListener(iAppForegroundEvent: IAppForegroundEvent) {
        if (!mEventList.contains(iAppForegroundEvent)) {
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
        LibLogs.logD("onActivityCreated act: $activity -> bundle: $bundle", TAG)
        mEventList.filterIsInstance<IActStatusEvent>().forEach {
            it.onCreate(activity)
        }
    }

    override fun onActivityStarted(activity: Activity) {
        LibLogs.logD("onActivityStarted: $activity", TAG)
        if (1 == mForegroundActCount.incrementAndGet() && !mIsChangingConfiguration.get()) {
            // 应用切到前台
            if (mForegroundStatus.compareAndSet(false, true)) {
                LibLogs.logD("enter FG: $activity", TAG)
                mEventList.toArray(arrayOf<IAppForegroundEvent>())
                    .forEach { it.onForegroundStateChange(true) }
            }
        }
        mIsChangingConfiguration.set(false)
    }

    override fun onActivityResumed(activity: Activity) {
        LibLogs.logD("onActivityResumed: $activity", TAG)
        mEventList.filterIsInstance<IActStatusEvent>().forEach {
            it.onShow(activity)
        }
    }

    override fun onActivityPaused(activity: Activity) {
        LibLogs.logD("onActivityPaused: $activity", TAG)
        mEventList.filterIsInstance<IActStatusEvent>().forEach {
            it.onHide(activity)
        }
    }

    override fun onActivityStopped(activity: Activity) {
        LibLogs.logD("onActivityStopped: $activity", TAG)
        if (0 == mForegroundActCount.decrementAndGet()) {
            // 应用切到后台
            if (mForegroundStatus.compareAndSet(true, false)) {
                LibLogs.logD("enter BG: $activity", TAG)
                mEventList.toArray(arrayOf<IAppForegroundEvent>())
                    .forEach { it.onForegroundStateChange(false) }
            }
        }
        mIsChangingConfiguration.set(activity.isChangingConfigurations)
    }

    override fun onActivityDestroyed(activity: Activity) {
        LibLogs.logD("onActivityDestroyed: $activity", TAG)
        mEventList.filterIsInstance<IActStatusEvent>().forEach {
            it.onDestroyed(activity)
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        LibLogs.logD("onActivitySaveInstanceState act: $activity -> bundle: $outState", TAG)
    }
}