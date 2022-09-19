package com.yh.libapp

import com.yh.appbasic.logger.ILogger
import com.yh.appbasic.logger.LogOwner
import com.yh.appbasic.logger.impl.TheLogFormatStrategy
import com.yh.appinject.InjectHelper

/**
 * Created by CYH on 2020-03-13 14:07
 */
object Lib1 : InjectHelper<Lib1Inject>(), ILogger {
    
    override fun onCreateLogOwner(logOwner: LogOwner) {
        logOwner.onCreateFormatStrategy {
            TheLogFormatStrategy.newBuilder("Lib1")
                .setMethodCount(5)
                .build()
        }
    }
}