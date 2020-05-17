package com.yh.appinject.logger.impl

import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.yh.appinject.logger.FormatStrategy
import com.yh.appinject.logger.LogAdapter

/**
 * Created by CYH on 2020/5/14 17:26
 */
class TheLogAdapter(private val formatStrategy: FormatStrategy) : LogAdapter {
    
    private var loggerConfig: Pair<Boolean, Int> = Pair(false, Log.ASSERT)
    
    fun isEnable() = loggerConfig.first
    fun logLevel() = loggerConfig.second
    
    fun setConfig(config: Pair<Boolean, Int>): TheLogAdapter {
        loggerConfig = config
        return this
    }
    
    override fun isLoggable(priority: Int, tag: String?): Boolean {
        return loggerConfig.first && priority >= loggerConfig.second
    }
    
    override fun log(priority: Int, @Nullable tag: String?, @NonNull message: String) {
        formatStrategy.log(priority, tag, message)
    }
}