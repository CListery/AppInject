@file:Suppress("unused")

package com.yh.appinject.logger

import android.database.Cursor
import androidx.annotation.NonNull
import androidx.annotation.Nullable

/**
 * Created by CYH on 2020/5/16 21:48
 */
object LibLogs {
    
    @JvmStatic
    @JvmOverloads
    fun logD(@NonNull msg: Any?, @Nullable tag: String? = null, @Nullable logAdapter: LogAdapter? = null, @Nullable vararg args: Any?) {
        LogsManager.get().with(logAdapter).t(tag)?.d(msg?.toString()
            ?: "", *args)
    }
    
    @JvmStatic
    @JvmOverloads
    fun logE(@NonNull msg: Any?, @Nullable tag: String? = null, @Nullable logAdapter: LogAdapter? = null, @Nullable throwable: Throwable? = null, @Nullable vararg args: Any?) {
        LogsManager.get().with(logAdapter).t(tag)?.e(throwable, msg?.toString()
            ?: "", *args)
    }
    
    @JvmStatic
    @JvmOverloads
    fun logI(@NonNull msg: Any?, @Nullable tag: String? = null, @Nullable logAdapter: LogAdapter? = null, @Nullable vararg args: Any?) {
        LogsManager.get().with(logAdapter).t(tag)?.i(msg?.toString()
            ?: "", *args)
    }
    
    @JvmStatic
    @JvmOverloads
    fun logV(@NonNull msg: Any?, @Nullable tag: String? = null, @Nullable logAdapter: LogAdapter? = null, @Nullable vararg args: Any?) {
        LogsManager.get().with(logAdapter).t(tag)?.v(msg?.toString()
            ?: "", *args)
    }
    
    @JvmStatic
    @JvmOverloads
    fun logW(@NonNull msg: Any?, @Nullable tag: String? = null, @Nullable logAdapter: LogAdapter? = null, @Nullable vararg args: Any?) {
        LogsManager.get().with(logAdapter).t(tag)?.w(msg?.toString()
            ?: "", *args)
    }
    
    /**
     * Tip: Use this for exceptional situations to log
     * ie: Unexpected errors etc
     */
    @JvmStatic
    @JvmOverloads
    fun logWTF(@NonNull msg: Any?, @Nullable tag: String? = null, @Nullable logAdapter: LogAdapter? = null, @Nullable vararg args: Any?) {
        LogsManager.get().with(logAdapter).t(tag)?.wtf(msg?.toString()
            ?: "", *args)
    }
    
    /**
     * Formats the given json content and print it
     */
    @JvmStatic
    @JvmOverloads
    fun logJSON(@Nullable json: Any?, @Nullable tag: String? = null, @Nullable logAdapter: LogAdapter? = null) {
        LogsManager.get().with(logAdapter).t(tag)?.json(json)
    }
    
    /**
     * Formats the given xml content and print it
     */
    @JvmStatic
    @JvmOverloads
    fun logXML(@Nullable xml: Any?, @Nullable tag: String? = null, @Nullable logAdapter: LogAdapter? = null) {
        LogsManager.get().with(logAdapter).t(tag)?.xml(xml)
    }
    
    /**
     * Formats the given cursor content and print it
     */
    @JvmStatic
    @JvmOverloads
    fun logCursor(@Nullable cursor: Cursor?, @NonNull justCurRow: Boolean = false, @Nullable tag: String? = null, @Nullable logAdapter: LogAdapter? = null) {
        LogsManager.get().with(logAdapter).t(tag)?.cursor(cursor, justCurRow)
    }
    
    /**
     * General log @JvmOverloads
     * function that accepts all configurations as parameter
     */
    @JvmStatic
    @JvmOverloads
    fun logP(@NonNull priority: Int, @NonNull msg: Any?, @Nullable tag: String? = null, @Nullable logAdapter: LogAdapter? = null, @Nullable throwable: Throwable? = null) {
        LogsManager.get().with(logAdapter).log(priority, tag, msg?.toString()
            ?: "", throwable)
    }
}