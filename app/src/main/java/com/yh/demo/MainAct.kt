package com.yh.demo

import android.content.Intent
import android.database.MatrixCursor
import android.os.Bundle
import android.util.Log
import com.yh.appbasic.logger.ILoggable
import com.yh.appbasic.logger.logCursor
import com.yh.appbasic.logger.logD
import com.yh.appbasic.logger.logE
import com.yh.appbasic.logger.logJSON
import com.yh.appbasic.logger.logW
import com.yh.appbasic.logger.owner.AppLogger
import com.yh.appbasic.ui.ViewBindingActivity
import com.yh.demo.databinding.ActMainBinding
import com.yh.demo.server.CrashJobServer
import com.yh.demo.server.MySafeJobServer
import com.yh.demo.server.TimerIntentServer
import com.yh.demo.server.TimerJobServer
import com.yh.libapp.A

/**
 * Created by CYH on 2020-03-13 14:24
 */
class MainAct : ViewBindingActivity<ActMainBinding>(), ILoggable {
    
    override fun binderCreator(savedInstanceState: Bundle?) = ActMainBinding.inflate(layoutInflater)
    
    override fun preInit(savedInstanceState: Bundle?) {
        Log.d("Tag", "I'm a log which you don't see easily, hehe")
        Log.d("json content", "{ \"key\": 3, \n \"value\": something}")
        Log.e("error", "There is a crash somewhere or any warning")
        AppLogger.on()
        logE("Custom tag for only one use", tag = "tag")
        logJSON("{ \"key\": 3, \"value\": something}")
        logD("this is a arr: ${listOf("foo", "bar")}")
        val map = HashMap<String, String>()
        map["key"] = "value"
        map["key1"] = "value2"
        logD("this is a map: \n$map")
        
        logD(this)
        logE(this)
        logE(RuntimeException("hhhhh"))
        logW(null)
        val cursor = MatrixCursor(arrayOf("id", "name", "age", "len"))
        cursor.newRow().add("id", 1).add("name", "cyh").add("age", 10).add("len", 109.6)
        cursor.newRow().add("id", 2).add("name", "cyh").add("age", 15).add("len", 138.75)
        cursor.newRow().add("id", 3).add("name", "cyh").add("age", 20).add("len", 175.643)
        cursor.newRow().add("id", 4).add("name", "cyh").add("age", 25).add("len", 178.456)
        logCursor(cursor, loggable = this)
        cursor.moveToPosition(2)
        logCursor(cursor, true, loggable = this)
        
        window.decorView.setOnTouchListener { v, event ->
            logW("$v touch $event", loggable = this)
            false
        }
    }
    
    override fun ActMainBinding.onInit(savedInstanceState: Bundle?) {
        btnOpenNext.setOnClickListener {
            logD("goNext")
            startActivity(Intent(mCtx, SecAct::class.java))
        }
        btnStartSafeServer.setOnClickListener {
            logD("startServer")
            MySafeJobServer.enqueueWork(mCtx)
        }
        btnStartCrashServer.setOnClickListener {
            logD("startCrashServer")
            CrashJobServer.enqueueWork(mCtx)
        }
        btnStartTimerIntentServer.setOnClickListener {
            logD("startTimerIntentServer")
            startService(Intent(mCtx, TimerIntentServer::class.java))
        }
        btnStartTimerJobServer.setOnClickListener {
            logD("startTimerJobServer")
            TimerJobServer.enqueueWork(mCtx)
        }
    }
    
    override fun onResume() {
        logD("onResume")
        super.onResume()
        A()
    }
    
}