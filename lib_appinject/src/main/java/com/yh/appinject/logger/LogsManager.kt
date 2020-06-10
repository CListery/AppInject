package com.yh.appinject.logger

import androidx.annotation.Nullable
import com.yh.appinject.InjectHelper
import com.yh.appinject.logger.impl.TheLogAdapter
import com.yh.appinject.logger.impl.TheLogFormatStrategy
import com.yh.appinject.logger.impl.TheLogPrinter

/**
 * 日志管理器
 *
 * Created by CYH on 2020/5/14 13:44
 */
class LogsManager private constructor() {

    companion object {
        @JvmStatic
        private val mInstances by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { LogsManager() }

        /**
         * 获取日志管理器单例
         */
        @JvmStatic
        fun get() = mInstances
    }

    /**
     * 日志输出器实例
     */
    private val mPrinter = TheLogPrinter()
    /**
     * 库与日志适配器的映射关系
     */
    private val mMap: HashMap<InjectHelper<*>, LogAdapter> = hashMapOf()
    /**
     * 库的默认日志适配器
     */
    private val mDefaultLibAdapter by lazy {
        TheLogAdapter(TheLogFormatStrategy.newBuilder().setFirstTag("Library").build())
    }
    /**
     * 应用的默认日志适配器
     */
    private val mDefaultAppAdapter by lazy {
        TheLogAdapter(TheLogFormatStrategy.newBuilder().setFirstTag("APP").build())
    }

    /**
     * 设置默认日志适配器的配置
     *
     * @param libConfig [mDefaultLibAdapter]
     * @param appConfig [mDefaultAppAdapter]
     * @see InjectHelper.loggerConfig
     */
    fun setDefLoggerConfig(@Nullable libConfig: Pair<Boolean, Int>? = null, @Nullable appConfig: Pair<Boolean, Int>? = null) {
        if (null != libConfig) {
            mDefaultLibAdapter.setConfig(libConfig)
        }
        if (null != appConfig) {
            mDefaultAppAdapter.setConfig(appConfig)
        }
    }

    /**
     * 安装库的日志适配器
     * @param [inject] [InjectHelper]
     * @param [adapter] [LogAdapter]
     */
    internal fun install(inject: InjectHelper<*>, adapter: LogAdapter) {
        mMap[inject] = adapter
    }

    /**
     * 卸载库的日志适配器
     * @param [inject] [InjectHelper]
     */
    internal fun uninstall(inject: InjectHelper<*>) {
        mMap.remove(inject)
    }

    /**
     * 根据[LogAdapter]获取[Printer]
     * @param [adapter] [LogAdapter]
     * @return [Printer]
     */
    @Nullable
    internal fun with(adapter: LogAdapter?): Printer {
        if (null == adapter) {
            return mPrinter.adapter(mDefaultLibAdapter)
        }
        return mPrinter.adapter(adapter)
    }

    /**
     * 根据[InjectHelper]获取[Printer]
     * @param [inject] [InjectHelper]
     * @return [Printer]
     */
    @Nullable
    internal fun with(inject: InjectHelper<*>?): Printer? {
        if (null == inject) {
            return mPrinter.adapter(mDefaultAppAdapter)
        }
        if (mMap.contains(inject)) {
            return mPrinter.adapter(mMap[inject])
        }
        return null
    }
}