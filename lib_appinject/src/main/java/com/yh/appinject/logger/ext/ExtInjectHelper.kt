@file:Suppress("unused") @file:JvmName("ExtInjectHelper")

package com.yh.appinject.logger.ext

import android.database.Cursor
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.yh.appinject.InjectHelper
import com.yh.appinject.logger.logCursor
import com.yh.appinject.logger.logD
import com.yh.appinject.logger.logE
import com.yh.appinject.logger.logI
import com.yh.appinject.logger.logJSON
import com.yh.appinject.logger.logP
import com.yh.appinject.logger.logV
import com.yh.appinject.logger.logW
import com.yh.appinject.logger.logWTF
import com.yh.appinject.logger.logXML

/**
 * Created by CYH on 2020/5/16 22:29
 */
@JvmOverloads
fun InjectHelper<*>.libD(@NonNull msg: String, @Nullable tag: String? = null, @Nullable vararg args: Any?) {
    logD(msg, tag, this, *args)
}

@JvmOverloads
fun InjectHelper<*>.libE(@NonNull msg: String, @Nullable tag: String? = null, @Nullable throwable: Throwable? = null, @Nullable vararg args: Any?) {
    logE(msg, tag, this, throwable, *args)
}

@JvmOverloads
fun InjectHelper<*>.libI(@NonNull msg: String, @Nullable tag: String? = null, @Nullable vararg args: Any?) {
    logI(msg, tag, this, *args)
}

@JvmOverloads
fun InjectHelper<*>.libV(@NonNull msg: String, @Nullable tag: String? = null, @Nullable vararg args: Any?) {
    logV(msg, tag, this, *args)
}

@JvmOverloads
fun InjectHelper<*>.libW(@NonNull msg: String, @Nullable tag: String? = null, @Nullable vararg args: Any?) {
    logW(msg, tag, this, *args)
}

/**
 * Tip: Use this for exceptional situations to log
 * ie: Unexpected errors etc
 */
@JvmOverloads
fun InjectHelper<*>.libWTF(@NonNull msg: String, @Nullable tag: String? = null, @Nullable vararg args: Any?) {
    logWTF(msg, tag, this, *args)
}

/**
 * Formats the given json content and print it
 */
@JvmOverloads
fun InjectHelper<*>.libJSON(@Nullable json: Any?, @Nullable tag: String? = null) {
    logJSON(json, tag, this)
}

/**
 * Formats the given xml content and print it
 */
@JvmOverloads
fun InjectHelper<*>.libXML(@Nullable xml: String?, @Nullable tag: String? = null) {
    logXML(xml, tag, this)
}

/**
 * Formats the given cursor content and print it
 */
@JvmOverloads
fun InjectHelper<*>.libCursor(@Nullable cursor: Cursor?, @NonNull justCurRow: Boolean = false, @Nullable tag: String? = null) {
    logCursor(cursor, justCurRow, tag, this)
}

/**
 * General log @JvmOverloads
 * function that accepts all configurations as parameter
 */
@JvmOverloads
fun InjectHelper<*>.libP(@NonNull priority: Int, @NonNull msg: String, @Nullable tag: String? = null, @Nullable throwable: Throwable? = null) {
    logP(priority, msg, tag, this, throwable)
}
