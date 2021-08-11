package com.yh.appinject.base

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.yh.appinject.ext.isCurrentLooper
import com.yh.appinject.ext.memoryId

abstract class ViewBindingActivity<VB : ViewBinding> : AppCompatActivity() {
    
    protected val mTag by lazy { "${this::class.java.simpleName}[$mActID]" }
    
    protected var _binder: VB? = null
    protected val uiLooper by lazy { Looper.getMainLooper() }
    protected val uiHandler by lazy { Handler(uiLooper, null) }
    
    @Suppress("UNCHECKED_CAST")
    protected val mAct: ViewBindingActivity<VB> by lazy { this }
    protected val mCtx: Context by lazy { this.baseContext }
    protected val mActID by lazy { mAct.memoryId }
    
    final override fun onCreate(savedInstanceState: Bundle?) {
        beforeOnCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        val binder = binderCreator(savedInstanceState)
        if(null == binder) {
            finish()
            return
        }
        
        _binder = binder
        setContentView(binder.root)
        
        preInit(savedInstanceState)
        binder.onInit(savedInstanceState)
    }
    
    open fun beforeOnCreate(savedInstanceState: Bundle?) {
    
    }
    
    open fun preInit(savedInstanceState: Bundle?){
    
    }
    
    abstract fun binderCreator(savedInstanceState: Bundle?): VB?
    
    abstract fun VB.onInit(savedInstanceState: Bundle?)
    
    open fun changeBinder(changer: VB.() -> Unit): Boolean {
        if(null == _binder) {
            return false
        }
        if(uiLooper.isCurrentLooper) {
            try {
                _binder?.changer()
            } catch(e: Exception) {
                e.printStackTrace()
                return false
            }
        } else {
            uiHandler.post {
                changeBinder(changer)
            }
        }
        return true
    }
    
}