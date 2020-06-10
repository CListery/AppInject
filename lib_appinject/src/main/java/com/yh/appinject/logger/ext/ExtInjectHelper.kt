@file:Suppress("unused") @file:JvmName("ExtInjectHelper")

package com.yh.appinject.logger.ext

import android.database.Cursor
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.yh.appinject.InjectHelper
import com.yh.appinject.logger.*

/**
 * 库日志输出工具
 *
 * 根据[com.yh.appinject.InjectHelper]区分
 *
 * Created by CYH on 2020/5/16 22:29
 */

/**
 * @see logD
 */
@JvmOverloads
fun InjectHelper<*>.libD(@NonNull msg: Any?, @Nullable tag: String? = null, @Nullable vararg args: Any?) {
    logD(msg, tag, this, *args)
}

/**
 * @see logE
 */
@JvmOverloads
fun InjectHelper<*>.libE(@NonNull msg: Any?, @Nullable tag: String? = null, @Nullable throwable: Throwable? = null, @Nullable vararg args: Any?) {
    logE(msg, tag, this, throwable, *args)
}

/**
 * @see logI
 */
@JvmOverloads
fun InjectHelper<*>.libI(@NonNull msg: Any?, @Nullable tag: String? = null, @Nullable vararg args: Any?) {
    logI(msg, tag, this, *args)
}

/**
 * @see logV
 */
@JvmOverloads
fun InjectHelper<*>.libV(@NonNull msg: Any?, @Nullable tag: String? = null, @Nullable vararg args: Any?) {
    logV(msg, tag, this, *args)
}

/**
 * @see logW
 */
@JvmOverloads
fun InjectHelper<*>.libW(@NonNull msg: Any?, @Nullable tag: String? = null, @Nullable vararg args: Any?) {
    logW(msg, tag, this, *args)
}

/**
 * @see logWTF
 */
@JvmOverloads
fun InjectHelper<*>.libWTF(@NonNull msg: Any?, @Nullable tag: String? = null, @Nullable vararg args: Any?) {
    logWTF(msg, tag, this, *args)
}

/**
 * @see logJSON
 */
@JvmOverloads
fun InjectHelper<*>.libJSON(@Nullable json: Any?, @Nullable tag: String? = null) {
    logJSON(json, tag, this)
}

/**
 * @see logXML
 */
@JvmOverloads
fun InjectHelper<*>.libXML(@Nullable xml: Any?, @Nullable tag: String? = null) {
    logXML(xml, tag, this)
}

/**
 * @see logCursor
 */
@JvmOverloads
fun InjectHelper<*>.libCursor(@Nullable cursor: Cursor?, @NonNull justCurRow: Boolean = false, @Nullable tag: String? = null) {
    logCursor(cursor, justCurRow, tag, this)
}

/**
 * @see logP
 */
@JvmOverloads
fun InjectHelper<*>.libP(@NonNull priority: Int, @NonNull msg: Any?, @Nullable tag: String? = null, @Nullable throwable: Throwable? = null) {
    logP(priority, msg, tag, this, throwable)
}
