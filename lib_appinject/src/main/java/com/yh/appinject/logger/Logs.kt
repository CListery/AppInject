@file:Suppress("unused", "FunctionName") @file:JvmName("Logs")

package com.yh.appinject.logger

import android.database.Cursor
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.yh.appinject.InjectHelper

/**
 * Created by CYH on 2020/5/14 13:59
 */
@JvmOverloads
fun logD(@NonNull msg: String, @Nullable tag: String? = null, @Nullable injectHelper: InjectHelper<*>? = null, @Nullable vararg args: Any?) {
    LogsManager.get().with(injectHelper)?.t(tag)?.d(msg, *args)
}

@JvmOverloads
fun logE(@NonNull msg: String, @Nullable tag: String? = null, @Nullable injectHelper: InjectHelper<*>? = null, @Nullable throwable: Throwable? = null, @Nullable vararg args: Any?) {
    LogsManager.get().with(injectHelper)?.t(tag)?.e(throwable, msg, *args)
}

@JvmOverloads
fun logI(@NonNull msg: String, @Nullable tag: String? = null, @Nullable injectHelper: InjectHelper<*>? = null, @Nullable vararg args: Any?) {
    LogsManager.get().with(injectHelper)?.t(tag)?.i(msg, *args)
}

@JvmOverloads
fun logV(@NonNull msg: String, @Nullable tag: String? = null, @Nullable injectHelper: InjectHelper<*>? = null, @Nullable vararg args: Any?) {
    LogsManager.get().with(injectHelper)?.t(tag)?.v(msg, *args)
}

@JvmOverloads
fun logW(@NonNull msg: String, @Nullable tag: String? = null, @Nullable injectHelper: InjectHelper<*>? = null, @Nullable vararg args: Any?) {
    LogsManager.get().with(injectHelper)?.t(tag)?.w(msg, *args)
}

/**
 * Tip: Use this for exceptional situations to log
 * ie: Unexpected errors etc
 */
@JvmOverloads
fun logWTF(@NonNull msg: String, @Nullable tag: String? = null, @Nullable injectHelper: InjectHelper<*>? = null, @Nullable vararg args: Any?) {
    LogsManager.get().with(injectHelper)?.t(tag)?.wtf(msg, *args)
}

/**
 * Formats the given json content and print it
 */
@JvmOverloads
fun logJSON(@Nullable json: Any?, @Nullable tag: String? = null, @Nullable injectHelper: InjectHelper<*>? = null) {
    LogsManager.get().with(injectHelper)?.t(tag)?.json(json)
}

/**
 * Formats the given xml content and print it
 */
@JvmOverloads
fun logXML(@Nullable xml: String?, @Nullable tag: String? = null, @Nullable injectHelper: InjectHelper<*>? = null) {
    LogsManager.get().with(injectHelper)?.t(tag)?.xml(xml)
}

/**
 * Formats the given cursor content and print it
 */
@JvmOverloads
fun logCursor(@Nullable cursor: Cursor?, @NonNull justCurRow: Boolean = false, @Nullable tag: String? = null, @Nullable injectHelper: InjectHelper<*>? = null) {
    LogsManager.get().with(injectHelper)?.t(tag)?.cursor(cursor, justCurRow)
}

/**
 * General log @JvmOverloads
 * function that accepts all configurations as parameter
 */
@JvmOverloads
fun logP(@NonNull priority: Int, @NonNull msg: String, @Nullable tag: String? = null, @Nullable injectHelper: InjectHelper<*>? = null, @Nullable throwable: Throwable? = null) {
    LogsManager.get().with(injectHelper)?.log(priority, tag, msg, throwable)
}
