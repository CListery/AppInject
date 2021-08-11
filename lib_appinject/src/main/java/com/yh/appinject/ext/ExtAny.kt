@file:JvmName("ExtAny")

package com.yh.appinject.ext

/**
 * 提供该对象的(伪)内存地址，只能用作参考
 *
 * * 请不要通过该值来比较对象是否相同
 */
val Any?.memoryId
    get():String {
        if(null == this){
            return toString()
        }
        return toString().replace(
            this::class.java.canonicalName ?: "", ""
        ).replace("@", "")
            .uppercase()
    }