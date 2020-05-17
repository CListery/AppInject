package com.yh.appinject.logger

import androidx.annotation.NonNull
import androidx.annotation.Nullable

/**
 * Used to determine how messages should be printed or saved.
 */
interface FormatStrategy {
    
    fun log(priority: Int, @Nullable onceOnlyTag: String?, @NonNull message: String)
}