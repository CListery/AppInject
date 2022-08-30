package com.yh.libapp

import android.content.Context
import com.yh.appbasic.logger.impl.TheLogFormatStrategy
import com.yh.appinject.InjectHelper

/**
 * Created by CYH on 2020-03-13 14:07
 */
class Lib1 : InjectHelper<Lib1Inject>() {
    
    override fun initializer(context: Context) {
        logger.onCreateFormatStrategy {
            TheLogFormatStrategy.newBuilder("Lib1")
                .setMethodCount(5)
                .build()
        }
    }
}