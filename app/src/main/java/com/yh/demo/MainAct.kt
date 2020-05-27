package com.yh.demo

import android.app.Activity
import android.content.Intent
import android.database.MatrixCursor
import android.os.Bundle
import android.util.Log
import android.view.View
import com.yh.appinject.logger.LibLogs
import com.yh.appinject.logger.LogsManager
import com.yh.appinject.logger.impl.TheLogAdapter
import com.yh.appinject.logger.impl.TheLogFormatStrategy
import com.yh.appinject.logger.logD
import com.yh.appinject.logger.logE
import com.yh.appinject.logger.logJSON
import com.yh.appinject.logger.logW
import com.yh.libapp.A

/**
 * Created by CYH on 2020-03-13 14:24
 */
class MainAct : Activity() {
    
    private val mLoggerAdapter = TheLogAdapter(TheLogFormatStrategy.newBuilder().setFirstTag("MainAct").build()).setConfig(true to Log.VERBOSE)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContentView(R.layout.act_main)
        
        Log.d("Tag", "I'm a log which you don't see easily, hehe")
        Log.d("json content", "{ \"key\": 3, \n \"value\": something}")
        Log.e("error", "There is a crash somewhere or any warning")
        LogsManager.get().setDefLoggerConfig(appConfig = Pair(true, Log.VERBOSE))
        logE("Custom tag for only one use", "tag")
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
        LibLogs.logCursor(cursor, logAdapter = mLoggerAdapter)
        cursor.moveToPosition(2)
        LibLogs.logCursor(cursor, true, logAdapter = mLoggerAdapter)
        
        window.decorView.setOnTouchListener { v, event ->
            LibLogs.logW("$v touch $event", logAdapter = mLoggerAdapter)
            false
        }
        
        A()
    }
    
    fun goNext(view: View) {
        logD("goNext")
        startActivity(Intent(this, SecAct::class.java))
    }
    
    override fun onResume() {
        logD("onResume")
        super.onResume()
    }
}