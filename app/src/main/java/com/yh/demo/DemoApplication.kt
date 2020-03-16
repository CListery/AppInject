package com.yh.demo

import android.app.Application
import android.widget.Toast
import com.yh.appinject.IBaseAppInject
import com.yh.appinject.ext.isMainProcess
import com.yh.libapp.Lib1

/**
 * Created by CYH on 2020-03-13 14:10
 */
class DemoApplication : Application(), IBaseAppInject {
    private var sApp: DemoApplication? = null
    private var mCtx: Application? = null

    override fun getApplication(): Application {
        return sApp!!
    }

    override fun showTipMsg(errorMsg: String) {
        Toast.makeText(mCtx, errorMsg, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate() {
        super.onCreate()

        if (!isMainProcess()) {
            return
        }

        sApp = this
        mCtx = getApplication()

        Lib1.get().register(this)

    }

}