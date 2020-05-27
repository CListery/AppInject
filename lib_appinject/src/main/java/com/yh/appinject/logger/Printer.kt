package com.yh.appinject.logger

import android.database.Cursor
import androidx.annotation.NonNull
import androidx.annotation.Nullable

/**
 * A proxy interface to enable additional operations.
 * Contains all possible Log message usages.
 */
interface Printer {
    
    fun addAdapter(@NonNull adapter: LogAdapter?)
    fun clearLogAdapters()
    fun t(@Nullable tag: String?): Printer?
//    fun d(@Nullable message: Any?)
    fun d(@NonNull message: String, @Nullable vararg args: Any?)
    fun i(@NonNull message: String, @Nullable vararg args: Any?)
    fun v(@NonNull message: String, @Nullable vararg args: Any?)
    fun w(@NonNull message: String, @Nullable vararg args: Any?)
    fun e(@NonNull message: String, @Nullable vararg args: Any?)
    fun e(@Nullable throwable: Throwable?, @NonNull message: String, @Nullable vararg args: Any?)
    fun wtf(@NonNull message: String, @Nullable vararg args: Any?)
    /**
     * Formats the given json content and print it
     */
    fun json(@Nullable jsonAny: Any?)
    
    /**
     * Formats the given xml content and print it
     */
    fun xml(xml: Any?)
    
    /**
     * Formats the given cursor content and print it
     */
    fun cursor(@Nullable cursor: Cursor?, justCurrentRow: Boolean)
    
    fun log(priority: Int, @Nullable tag: String?, @Nullable message: String?, @Nullable throwable: Throwable?)
}