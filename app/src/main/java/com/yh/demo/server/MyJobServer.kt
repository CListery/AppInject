package com.yh.demo.server

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import androidx.core.app.SafeJobIntentService
import com.yh.appinject.logger.logD

class MyJobServer : SafeJobIntentService() {
    
    companion object {
        
        /**
         * Unique job ID for this service.
         */
        private const val JOB_ID = 1024
        
        @JvmStatic
        fun enqueueWork(context: Context) {
            logD("enqueueWork: $context")
            enqueueWork(context, MyJobServer::class.java, JOB_ID, Intent())
        }
    }
    
    override fun onHandleWork(intent: Intent) {
        logD("onHandleWork")
    }
    
    override fun onBind(intent: Intent): IBinder? {
        logD("onBind")
        return super.onBind(intent).also {
            Handler().post {
                Thread.sleep(10000)
            }
        }
    }
    
}